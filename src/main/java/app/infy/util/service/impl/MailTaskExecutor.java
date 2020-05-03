package app.infy.util.service.impl;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;

public final class MailTaskExecutor {
	
	private ConcurrentHashMap<String, MimeMessage> mailMap;
	private JavaMailSender emailSender;
	
	private final int maxConcurrentTask = 5;
	
	public MailTaskExecutor(JavaMailSender emailSender) {
		mailMap = new ConcurrentHashMap<String, MimeMessage>();
		this.emailSender = emailSender;
	}
	
	public void sendAsyncMail(String requestId, MimeMessage mimeMessage) {
		if(mimeMessage != null) {
			mailMap.put(requestId, mimeMessage);
		}
	}
	
	/**
	 * This scheduled cron runs every 3 minutes. <br/>
	 * This cron picks up data from mailMap concurrenthashmap. <br/>
	 * First the MimeMessage object is removed from mailMap. If the mail is sent, then log is written.<br/>
	 * If the mail is not sent, then it is put back in the mailMap. To be tried again after 2+minutes.
	 */
	@Scheduled(cron = "0 0/3 * * * ?")
	public void processMails() {
		System.out.println("Mail job started.");
		int counter = 0;
		Map<String, MimeMessage> dataMap = new HashMap<>();
		Enumeration<String> keys = mailMap.keys();
		while(keys.hasMoreElements() && counter++<maxConcurrentTask) {
			String currKey = keys.nextElement();
			dataMap.put(currKey, mailMap.remove(currKey));
		}
		final Map<String, MimeMessage> finalDataMap = new HashMap<>(dataMap);
		System.out.println("Mail job size:"+dataMap.size());
		dataMap.clear();
		dataMap = null;
		
		List<String> outList = null;
		if(finalDataMap.size()>0) {
			outList = finalDataMap.entrySet().parallelStream().map(
					inputData->{
						
						String inKey = inputData.getKey();
						MimeMessage inMM = inputData.getValue();
						boolean stat = false;
						try {
							emailSender.send(inMM);
							System.out.println("MailJob: sent mail for key "+inKey);
							stat = true;
						} catch(MailAuthenticationException | MailSendException mae) {
							System.out.println(mae.getMessage());
						}
						
						//stat false means mail job didn't ran
						//returning the key, so that it can be put again in the queue.
						//there is a possibility of a mail job to wait forever.
						//need to implement a remove method for multiple failures.
						if(stat) {
							return null;
						} else {
							return inKey;
						}
					}
				).collect(Collectors.toList());
			outList.removeIf(t->(t==null));
			mailMap.putAll(outList.parallelStream().collect(
					Collectors.toMap(t->t, t->finalDataMap.get(t))
				));
			finalDataMap.clear();
			outList = null;
			keys = null;
		} else {
			System.out.println("No mails to send!");
		}
	}
}

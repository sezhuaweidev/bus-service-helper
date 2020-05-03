package app.infy.util.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import app.infy.util.model.Mail;
import app.infy.util.service.impl.MailTaskExecutor;


@Service
public class EmailSenderService {

	private JavaMailSender emailSender;
    private SpringTemplateEngine templateEngine;
    private MailTaskExecutor mailTaskExecutor;
	
    public EmailSenderService(
    		JavaMailSender emailSender, 
    		SpringTemplateEngine templateEngine, 
    		MailTaskExecutor mailTaskExecutor) {
    	
		super();
		this.emailSender = emailSender;
		this.templateEngine = templateEngine;
		this.mailTaskExecutor = mailTaskExecutor;
	}
    
    public void sendAckEmail(String requestId, Mail mail) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(mail.getProps());
        String html = templateEngine.process("shuttle_ack_mail", context);
        helper.setTo(mail.getMailTo());
        helper.setCc(mail.getMailCc());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());
        mailTaskExecutor.sendAsyncMail(requestId, message);
        return;
    }

    public void sendReqEmail(String requestId, Mail mail) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(mail.getProps());
        String html = templateEngine.process("shuttle_req_mail", context);
        helper.setTo(mail.getMailTo());
        helper.setCc(mail.getMailCc());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());
        mailTaskExecutor.sendAsyncMail(requestId, message);
        return;
    }
    public void sendErrorEmail(String requestId, Mail mail) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(mail.getProps());
        String html = templateEngine.process("shuttle_err_mail", context);
        helper.setTo(mail.getMailTo());
        helper.setCc(mail.getMailCc());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());
        mailTaskExecutor.sendAsyncMail(requestId, message);
        return;
    }
}


package app.infy.util.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import app.infy.util.entity.EmployeeDetail;
import app.infy.util.entity.InfyDc;
import app.infy.util.entity.ShuttleRequest;
import app.infy.util.entity.ShuttleTiming;
import app.infy.util.exception.ApplicationException;
import app.infy.util.helper.MessageConstants;
import app.infy.util.helper.StatusEnum;
import app.infy.util.model.FormShuttleRequest;
import app.infy.util.model.Mail;
import app.infy.util.model.ShuttleBookingStatus;
import app.infy.util.repository.EmployeeDetailRepository;
import app.infy.util.repository.InfyDcRepository;
import app.infy.util.repository.ShuttleRequestRepository;
import app.infy.util.repository.ShuttleTimingRepository;
import app.infy.util.service.EmailSenderService;
import app.infy.util.service.ShuttleService;

@Service
public class ShuttleServiceImpl implements ShuttleService {

	private Converter<FormShuttleRequest, ShuttleRequest> formToShuttleReuqestConverter;
	private EmployeeDetailRepository employeeDetailRepository;
	private ShuttleRequestRepository shuttleRequestRepository;
	private ShuttleTimingRepository shuttleTimingRepository;
	private EmailSenderService emailSenderService;
	private Converter<ShuttleRequest, ShuttleBookingStatus> entityToShuttleRequestModelConverter;
	private CacheManager cacheManager;
	
	private InfyDcRepository infyDcRepository;
	
	public ShuttleServiceImpl(
			EmployeeDetailRepository employeeDetailRepository,
			ShuttleRequestRepository shuttleRequestRepository,
			ShuttleTimingRepository shuttleTimingRepository,
			Converter<FormShuttleRequest, ShuttleRequest> formToShuttleReuqestConverter,
			Converter<ShuttleRequest, ShuttleBookingStatus> entityToShuttleRequestModelConverter,
			EmailSenderService emailSenderService,
			InfyDcRepository infyDcRepository,
			CacheManager cacheManager ) {
		
		this.employeeDetailRepository = employeeDetailRepository;
		this.shuttleRequestRepository = shuttleRequestRepository;
		this.shuttleTimingRepository = shuttleTimingRepository;
		this.formToShuttleReuqestConverter = formToShuttleReuqestConverter;
		this.entityToShuttleRequestModelConverter = entityToShuttleRequestModelConverter;
		this.emailSenderService = emailSenderService;
		this.infyDcRepository = infyDcRepository;
		this.cacheManager = cacheManager;
	}
	
	@Override
	public String prepareShuttleRequest(FormShuttleRequest shuttleRequest) {
		// TODO Auto-generated method stub
		//System.out.println(shuttleTimingRepository.getById("STL0800").toString());
		//System.out.println(shuttleTimingRepository.getById("STL").toString());
		//System.out.println(shuttleRequestRepository.save(formToShuttleReuqestConverter.convert(shuttleRequest)));
		//System.out.println(Arrays.deepToString(shuttleTimingRepository.getAll().toArray()));
		
		ShuttleRequest sr = formToShuttleReuqestConverter.convert(shuttleRequest);
		if(shuttleRequestRepository.existsById(sr.getRequestId())) {
			throw new ApplicationException("REQUEST_ALREADY_EXISTS");
		} else {
			ShuttleRequest usr = shuttleRequestRepository.save(sr);
			
			//putting data in a 28 minute time to live cache.
			//key: shuttleRequestId, value: current epochmilis
			cacheManager.getCache("shuttleRequestIdList").put(usr.getRequestId(), new Date().getTime());
			
			//send email
			System.out.println("START... Sending email");
			String shuttleRequestId = usr.getRequestId();//get this id from ShuttleRequest model
	        Mail mail = new Mail();
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	        mail.setFrom("sez.huawei.dev@gmail.com");//replace with your desired email
	        mail.setMailTo("riddhi.sohampaul@gmail.com");//replace with your desired email
	        mail.setMailCc(new String[] {});
	        mail.setSubject("Shuttle Pass Request ACK");
	        //email template parameter
	        Map<String, Object> model = new HashMap<String, Object>();
	        model.put("name", "Manager!");//put manager name
	        model.put("location", "Infosys DC");
	        model.put("sign", "Transportation Team");
	        model.put("approveUrl", "http://localhost:8082/api/shuttleservice/approved");// put shuttle request time
	        model.put("rejectUrl", "http://localhost:8082/api/shuttleservice/rejected");// put shuttle request time
	        model.put("dateTime", sdf.format(new Date()));// put shuttle request time
	        mail.setProps(model);
	        try {
				emailSenderService.sendEmail(mail);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ApplicationException(MessageConstants.EMAIL_FAILED+e.getMessage());
			}
	        System.out.println("END... Email sent success");
	        return usr.getRequestId();
			
		}
	}

	@Override
	public ShuttleBookingStatus getShuttleStatusById(String id) {
		// TODO Auto-generated method stub
		
		if(shuttleRequestRepository.existsById(id)) {
			ShuttleRequest sr = shuttleRequestRepository.findById(id).orElse(new ShuttleRequest());
			
			ShuttleBookingStatus shuttleBookingStatus = new ShuttleBookingStatus();
			shuttleBookingStatus.setRequestId(id);
			shuttleBookingStatus.setRequesterName(employeeDetailRepository.findById(sr.getRequester()).orElse(new EmployeeDetail()).getEmpName());
			shuttleBookingStatus.setApproverName(employeeDetailRepository.findById(sr.getApprover()).orElse(new EmployeeDetail()).getEmpName());
			ShuttleTiming st = shuttleTimingRepository.findById(sr.getShuttleId()).orElse(new ShuttleTiming());
			shuttleBookingStatus.setShuttleStartTime(st.getStartTime().toString());
			shuttleBookingStatus.setShuttleReturnTime1(st.getReturnTime1().toString());
			shuttleBookingStatus.setShuttleReturnTime2(st.getReturnTime2().toString());
			shuttleBookingStatus.setReason(sr.getReason());
			shuttleBookingStatus.setForDate(sr.getForDate());
			shuttleBookingStatus.setStatus(sr.getStatus());
			
			return shuttleBookingStatus;
		} else {
			throw new ApplicationException(MessageConstants.SHUTTLE_STATUS_NOT_FOUND);
		}
	}

	@Override
	public String updateShuttleBookingStatus(String shuttleRequestId, StatusEnum statusEnum) {
		
		if(!shuttleRequestRepository.existsById(shuttleRequestId)) {
			throw new ApplicationException(MessageConstants.SHUTTLE_REQUEST_ID_INVALID);
		} else {
			ShuttleRequest shuttleRequest = shuttleRequestRepository.findById(shuttleRequestId).orElse(new ShuttleRequest());
			System.out.println("Received: "+shuttleRequest.getStatus());
			if(!shuttleRequest.getStatus().equalsIgnoreCase("PENDING") ) {
				throw new ApplicationException(MessageConstants.STATUS_ALREADY_UPDATED);
			} else {
				shuttleRequest.setStatus(statusEnum.name().toUpperCase());
				shuttleRequestRepository.save(shuttleRequest);
				
				if(shuttleRequest.getStatus().equalsIgnoreCase(StatusEnum.approved.name())) {
					//send email
			        Mail mail = new Mail();
			        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			        mail.setFrom("sez.huawei.dev@gmail.com");//replace with your desired email
			        mail.setMailTo("riddhi.sohampaul@gmail.com");//replace with your desired email
			        mail.setMailCc(new String[] {"sohampaul74@gmail.com"});
			        mail.setSubject("Shuttle Pass - Approved");
			        //email template parameter
			        Map<String, Object> model = new HashMap<String, Object>();
			        model.put("name", "Manager!");//put manager name
			        model.put("location", "Infosys DC");
			        model.put("sign", "Transportation Team");
			        model.put("approveUrl", "http://localhost:8082/api/shuttleservice/approved");// put shuttle request time
			        model.put("rejectUrl", "http://localhost:8082/api/shuttleservice/rejected");// put shuttle request time
			        model.put("dateTime", sdf.format(new Date()));// put shuttle request time
			        mail.setProps(model);
					try {
						emailSenderService.sendEmail(mail);
					} catch (MessagingException | IOException e) {
						e.printStackTrace();
						throw new ApplicationException(MessageConstants.EMAIL_FAILED_BUT_APPROVED);
					}
				}
				
				return MessageConstants.SUCCESS;
			}
		}
	}

	@Override
	public void processShuttleRequests() {
		// TODO Auto-generated method stub

	}

	@Override
	@Cacheable("shuttletiming")
	public List<ShuttleTiming> getAllShuttles() {
		// TODO Auto-generated method stub
		return shuttleTimingRepository.findAll();
	}

	@Override
	@Cacheable("infydc")
	public List<InfyDc> getAllInfyDcs() {
		return infyDcRepository.findAll();
	}

}

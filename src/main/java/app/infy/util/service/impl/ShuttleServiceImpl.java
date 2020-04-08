package app.infy.util.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import app.infy.util.BusServiceHelper;
import app.infy.util.entity.EmployeeDetail;
import app.infy.util.entity.InfyCountry;
import app.infy.util.entity.InfyDc;
import app.infy.util.entity.InfyRegion;
import app.infy.util.entity.ShuttleRequest;
import app.infy.util.entity.ShuttleTiming;
import app.infy.util.exception.ApplicationException;
import app.infy.util.exception.ControllerException;
import app.infy.util.helper.MessageConstants;
import app.infy.util.helper.StatusEnum;
import app.infy.util.model.FormShuttleRequest;
import app.infy.util.model.Mail;
import app.infy.util.model.ShuttleBookingStatus;
import app.infy.util.repository.EmployeeDetailRepository;
import app.infy.util.repository.InfyCountryRepository;
import app.infy.util.repository.InfyDcRepository;
import app.infy.util.repository.InfyRegionRepository;
import app.infy.util.repository.ShuttleRequestRepository;
import app.infy.util.repository.ShuttleTimingRepository;
import app.infy.util.service.EmailSenderService;
import app.infy.util.service.ShuttleService;

@Service
public class ShuttleServiceImpl implements ShuttleService {

	private EmployeeDetailRepository employeeDetailRepository;
	private ShuttleRequestRepository shuttleRequestRepository;
	private ShuttleTimingRepository shuttleTimingRepository;
	private InfyDcRepository infyDcRepository;
	private InfyRegionRepository infyRegionRepository;
	private InfyCountryRepository infyCountryRepository;
	
	private EmailSenderService emailSenderService;

	private Converter<FormShuttleRequest, ShuttleRequest> formToShuttleReuqestConverter;
	//private Converter<ShuttleRequest, ShuttleBookingStatus> entityToShuttleRequestModelConverter;
	
	private CacheManager cacheManager;
	
	
	public ShuttleServiceImpl(
			EmployeeDetailRepository employeeDetailRepository,
			ShuttleRequestRepository shuttleRequestRepository,
			ShuttleTimingRepository shuttleTimingRepository,
			InfyRegionRepository infyRegionRepository,
			InfyCountryRepository infyCountryRepository,
			Converter<FormShuttleRequest, ShuttleRequest> formToShuttleReuqestConverter,
			//Converter<ShuttleRequest, ShuttleBookingStatus> entityToShuttleRequestModelConverter,
			EmailSenderService emailSenderService,
			InfyDcRepository infyDcRepository,
			CacheManager cacheManager ) {
		
		this.employeeDetailRepository = employeeDetailRepository;
		this.shuttleRequestRepository = shuttleRequestRepository;
		this.shuttleTimingRepository = shuttleTimingRepository;
		this.infyRegionRepository = infyRegionRepository;
		this.infyCountryRepository = infyCountryRepository;
		this.formToShuttleReuqestConverter = formToShuttleReuqestConverter;
		//this.entityToShuttleRequestModelConverter = entityToShuttleRequestModelConverter;
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
		
		if(!employeeDetailRepository.existsById(shuttleRequest.getRequester()) ) {
			throw new ApplicationException(MessageConstants.EMPLOYEE_ID_NOT_FOUND);
		}
		
		ShuttleRequest sr = formToShuttleReuqestConverter.convert(shuttleRequest);
		
		EmployeeDetail ed = employeeDetailRepository.findById(shuttleRequest.getRequester()).orElse(null);
		sr.setApprover(ed.getEmpManagerId());
		
		if(shuttleRequestRepository.existsById(sr.getRequestId())) {
			throw new ApplicationException("REQUEST_ALREADY_EXISTS");
		} else {
			ShuttleRequest usr = shuttleRequestRepository.save(sr);
			
			EmployeeDetail managerDetail = employeeDetailRepository.findById(ed.getEmpManagerId()).orElse(new EmployeeDetail());
			
			//putting data in a 28 minute time to live cache.
			//key: shuttleRequestId, value: current epochmilis
			BusServiceHelper.APPROVAL_MAP.put(usr.getRequestId(), new Date().getTime());
			
			//send email
			System.out.println("START... Sending email");
	        Mail mail = new Mail();
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	        mail.setFrom("sez.huawei.dev@gmail.com");//replace with your desired email
	        mail.setMailTo(managerDetail.getEmpMail());//replace with your desired email
	        mail.setMailCc(new String[] { });
	        mail.setSubject("Shuttle Pass Request - ACK");
	        //email template parameter
	        Map<String, Object> model = new HashMap<String, Object>();
	        model.put("employeeName", ed.getEmpName());
	        model.put("employeeId", ed.getEmpId());
	        model.put("name", managerDetail.getEmpName());
	        model.put("location", infyDcRepository.findById(shuttleRequest.getDcFrom()).orElse(new InfyDc()).getValue());
	        model.put("sign", "Transportation Team");
	        model.put("approveUrl", "http://localhost:8082/api/shuttleservice/"+usr.getRequestId()+"/approved");// put shuttle request time
	        model.put("rejectUrl", "http://localhost:8082/api/shuttleservice/"+usr.getRequestId()+"/rejected");// put shuttle request time
	        model.put("dateTime", sdf.format(new Date())+" "+shuttleTimingRepository.findById(shuttleRequest.getShuttleId()).orElse(new ShuttleTiming()).getStartTime());// put shuttle request time
	        mail.setProps(model);
	        try {
				emailSenderService.sendReqEmail(mail);
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
			shuttleBookingStatus.setDcFrom(infyDcRepository.findById(sr.getDcFrom()).orElse(new InfyDc()).getValue());
			shuttleBookingStatus.setDcTo(infyDcRepository.findById(sr.getDcTo()).orElse(new InfyDc()).getValue());
			
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
			if(!shuttleRequest.getStatus().equalsIgnoreCase("PENDING")) {
				throw new ApplicationException(MessageConstants.STATUS_ALREADY_UPDATED);
			} else {
				shuttleRequest.setStatus(statusEnum.name().toUpperCase());
				shuttleRequestRepository.save(shuttleRequest);
				
				EmployeeDetail ed = employeeDetailRepository.findById(shuttleRequest.getRequester()).orElse(new EmployeeDetail());
				EmployeeDetail managerDetail = employeeDetailRepository.findById(shuttleRequest.getApprover()).orElse(new EmployeeDetail());
				if(shuttleRequest.getStatus().equalsIgnoreCase(StatusEnum.approved.name())) {
					//send email
			        Mail mail = new Mail();
			        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			        mail.setFrom("sez.huawei.dev@gmail.com");//replace with your desired email
			        mail.setMailTo(infyDcRepository.findById(shuttleRequest.getDcFrom()).orElse(new InfyDc()).getTraveDeskMail());//transport desk team
			        mail.setMailCc(new String[] {ed.getEmpMail(),managerDetail.getEmpMail()});
			        mail.setSubject("Shuttle Pass - "+shuttleRequest.getStatus());
			        //email template parameter
			        Map<String, Object> model = new HashMap<String, Object>();
			        model.put("name", "Transportation Team");//put manager name
			        model.put("empName", ed.getEmpName());
			        model.put("empId", ed.getEmpId());
			        model.put("location", infyDcRepository.findById(shuttleRequest.getDcFrom()).orElse(new InfyDc()).getValue());
			        model.put("sign", "Transportation Team");
			        model.put("status", shuttleRequest.getStatus());
			        model.put("dateTime", sdf.format(new Date())+" "+shuttleTimingRepository.findById(shuttleRequest.getShuttleId()).orElse(new ShuttleTiming()).getStartTime());// put shuttle request time
			        mail.setProps(model);
					try {
						emailSenderService.sendAckEmail(mail);
				        System.out.println("END... Email sent success");
					} catch (MessagingException | IOException e) {
						e.printStackTrace();
						throw new ApplicationException(MessageConstants.EMAIL_FAILED_BUT_APPROVED);
					}
				} else {
					//send email
			        Mail mail = new Mail();
			        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			        mail.setFrom("sez.huawei.dev@gmail.com");//replace with your desired email
			        mail.setMailTo(ed.getEmpMail());//transport desk team
			        mail.setMailCc(new String[] {managerDetail.getEmpMail()});
			        mail.setSubject("Shuttle Pass - "+shuttleRequest.getStatus());
			        //email template parameter
			        Map<String, Object> model = new HashMap<String, Object>();
			        model.put("name", ed.getEmpName());//put manager name
			        model.put("location", infyDcRepository.findById(shuttleRequest.getDcFrom()).orElse(new InfyDc()).getValue());
			        model.put("sign", "Transportation Team");
			        model.put("status", shuttleRequest.getStatus());
			        model.put("dateTime",sdf.format(new Date())+" "+shuttleTimingRepository.findById(shuttleRequest.getShuttleId()).orElse(new ShuttleTiming()).getStartTime());// put shuttle request time
			        mail.setProps(model);
					try {
						emailSenderService.sendErrorEmail(mail);
				        System.out.println("END... Email sent success");
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

	@Override
	@Cacheable("infyregion")
	public List<InfyRegion> getAllInfyRegion() {
		return infyRegionRepository.findAll();
	}

	@Override
	@Cacheable(cacheNames = "infycountry", key="")
	public List<InfyCountry> getInfyCountry(String continent) {
		if(continent.equalsIgnoreCase("all") ) {
			return infyCountryRepository.findAll();
		}
		if(!infyRegionRepository.existsById(continent)) { throw new ControllerException(MessageConstants.WRONG_CONTINENT_NAME); }
		return infyCountryRepository.findByContinent(continent);
	}

	/**
	 * This method contains auto approve logic.
	 * If shuttle pass request not approved within 28 minutes, it gets auto approved.
	 */
	@Scheduled(cron = "0 0/1 * * * ?")
	public void processShuttleRequests() {
		Enumeration<String> keys = BusServiceHelper.APPROVAL_MAP.keys();
		if(!keys.hasMoreElements()) {
			System.out.println("No elements in cache");
		}
		while(keys.hasMoreElements()) {
			String key = keys.nextElement();
			Long receivedEpoch = Long.valueOf(BusServiceHelper.APPROVAL_MAP.get(key));
			Long currentEpoch = new Date().getTime();
			
			if((currentEpoch-receivedEpoch)>(5*60*1000)) {
				String status =shuttleRequestRepository.findById(key).orElse(new ShuttleRequest()).getStatus(); 
				if(status.equalsIgnoreCase("PENDING")) {
					String data = updateShuttleBookingStatus(key, StatusEnum.approved);
					System.out.println("Auto-approved request id:"+key);
					BusServiceHelper.APPROVAL_MAP.remove(key);
				} else {
					BusServiceHelper.APPROVAL_MAP.remove(key);
				}
			} else {
				System.out.println("time elpased:"+(currentEpoch-receivedEpoch));
			}
			
			
		}
	}

	@Override
	public List<ShuttleRequest> findShuttleRequestByMngIdAndDate(Integer approverId,String forDate) {
		return shuttleRequestRepository.findShuttleRequestByMngIdAndDate(approverId,forDate);
	}
	
}

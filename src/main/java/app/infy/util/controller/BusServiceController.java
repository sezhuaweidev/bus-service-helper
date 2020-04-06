package app.infy.util.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import app.infy.util.model.Mail;
import app.infy.util.exception.ApplicationException;
import app.infy.util.helper.AppResponse;
import app.infy.util.helper.MessageConstants;
import app.infy.util.helper.StatusEnum;
import app.infy.util.model.ShuttleBookingAck;
import app.infy.util.model.ShuttleBookingStatus;
import app.infy.util.model.ShuttleBookingStatusUpdate;
import app.infy.util.model.ShuttleRequest;
import app.infy.util.service.EmailSenderService;
import app.infy.util.service.ShuttleService;

@RestController
@RequestMapping("shuttleservice")
public class BusServiceController {

	private ShuttleService shuttleService;
	@Autowired
	EmailSenderService emailSenderService;
	
	public BusServiceController(ShuttleService shuttleService) {
		this.shuttleService = shuttleService;
	}
	
	@PostMapping(value = "", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public AppResponse<ShuttleBookingAck> saveShuttleRequest(
			@RequestBody @Valid ShuttleRequest shuttleRequest, 
			BindingResult br) {
		//validation
		if(br.hasErrors()) { throw new ApplicationException(br.getAllErrors().parallelStream().map(t->t.getDefaultMessage()).reduce((t1,t2)->(t1+", "+t2)).orElse(null)); }
		
		//logic
		String saveStatus = shuttleService.prepareShuttleRequest(shuttleRequest);
		
		
		//send email
		System.out.println("START... Sending email");
		String shuttleRequestId = "";//get this id from ShuttleRequest model
        Mail mail = new Mail();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        mail.setFrom("sez.huawei.dev@gmail.com");//replace with your desired email
        mail.setMailTo("surya.sahu92@gmail.com");//replace with your desired email
        mail.setSubject("Shuttle Pass!");
        //email template parameter
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", "Manager!");//put manager name
        model.put("location", "Infosys DC");
        model.put("sign", "Transportation Team");
        model.put("approveUrl", MessageConstants.BETA_BASE_URL+shuttleRequestId+"/approve");// put shuttle request time
        model.put("rejectUrl", MessageConstants.BETA_BASE_URL+shuttleRequestId+"/reject");// put shuttle request time
        model.put("dateTime", sdf.format(new Date()));// put shuttle request time
        mail.setProps(model);
        try {
			emailSenderService.sendEmail(mail);
		} catch (Exception e) {
			throw new ApplicationException(MessageConstants.EMAIL_FAILED+e.getMessage());
		}
        System.out.println("END... Email sent success");
		
		return null;
	}
	
	@GetMapping(value="{shuttleRequestId}",produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
	public AppResponse<ShuttleBookingStatus> getShuttleRequestStatus(
			@PathVariable(name = "shuttleRequestId",required = true) String id) {
		//validation
		if(!StringUtils.hasText(id) || id.length() < 4 ) {
			throw new ApplicationException(MessageConstants.PROVIDED_ID_INVALID);
		}
		
		//logic
		ShuttleBookingStatus shuttleBookingStatus = shuttleService.getShuttleStatusById(id);
		
		
		return null;
	}
	
	@PutMapping(value="{shuttleRequestId}/{status}",produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
	public AppResponse<ShuttleBookingStatusUpdate> updateShuttleRequestStatus(
			@PathVariable(name="shuttleRequestId", required = true) String shuttleRequestId, 
			@PathVariable(name="status",required = true) String status) {
		//validation
		if(!StringUtils.hasText(shuttleRequestId) || shuttleRequestId.length() < 4 ) {
			throw new ApplicationException(MessageConstants.PROVIDED_ID_INVALID);
		}
		StatusEnum statusEnum = null;
		try {
			statusEnum = StatusEnum.valueOf(status);
		} catch(IllegalArgumentException | NullPointerException npe) {
			throw new ApplicationException(MessageConstants.PROVIDED_STATUS_INVALID);
		}
		
		//logic
		String updateStatus = shuttleService.updateShuttleBookingStatus(shuttleRequestId, statusEnum);
		
		
		return null;
	}
}

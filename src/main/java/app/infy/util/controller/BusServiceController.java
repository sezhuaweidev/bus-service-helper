package app.infy.util.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.infy.util.exception.ApplicationException;
import app.infy.util.helper.AppResponse;
import app.infy.util.helper.MessageConstants;
import app.infy.util.helper.StatusEnum;
import app.infy.util.model.FormShuttleRequest;
import app.infy.util.model.ShuttleBookingStatus;
import app.infy.util.service.EmailSenderService;
import app.infy.util.service.ShuttleService;

@RestController
@RequestMapping("shuttleservice")
public class BusServiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(BusServiceController.class);
	
	private ShuttleService shuttleService;
	@Autowired
	EmailSenderService emailSenderService;
	
	public BusServiceController(ShuttleService shuttleService) {
		this.shuttleService = shuttleService;
	}
	
	@PostMapping(value = "", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code=HttpStatus.CREATED)
	public AppResponse<String> saveShuttleRequest(
			@RequestBody @Valid FormShuttleRequest shuttleRequest, 
			BindingResult br) {
		logger.info("saveShuttleRequest called with "+shuttleRequest.toString());
		//validation
		if(br.hasErrors()) { throw new ApplicationException(br.getAllErrors().parallelStream().map(t->t.getDefaultMessage()).reduce((t1,t2)->(t1+", "+t2)).orElse(null)); }
		
		//logic
		String saveStatus = shuttleService.prepareShuttleRequest(shuttleRequest);
		return new AppResponse<String>(saveStatus);
	}
	
	@GetMapping(value="{shuttleRequestId}",produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
	@ResponseStatus(code=HttpStatus.OK)
	public AppResponse<ShuttleBookingStatus> getShuttleRequestStatus(
			@PathVariable(name = "shuttleRequestId",required = true) String id) {
		logger.info("getShuttleRequestStatus called with "+id);
		//validation
		if(!StringUtils.hasText(id) || id.length() < 4 ) {
			throw new ApplicationException(MessageConstants.PROVIDED_ID_INVALID);
		}
		
		//logic
		ShuttleBookingStatus shuttleBookingStatus = shuttleService.getShuttleStatusById(id);
		return new AppResponse<ShuttleBookingStatus>(shuttleBookingStatus);
	}
	
	@GetMapping(value="{shuttleRequestId}/{status}/{reason}",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code=HttpStatus.OK)
	public AppResponse<String> updateShuttleRequestStatus(
			@PathVariable(name="shuttleRequestId", required = true) String shuttleRequestId, 
			@PathVariable(name="status",required = true) String status,
			@PathVariable(name="reason") String reason) {
		logger.info("updateShuttleRequestStatus called with "+shuttleRequestId+ " and status:"+status+"reason:"+reason);
		//validation
		if(!StringUtils.hasText(shuttleRequestId) || shuttleRequestId.length() < 4 ) {
			throw new ApplicationException(MessageConstants.PROVIDED_ID_INVALID);
		}
		StatusEnum statusEnum = null;
		try {
			statusEnum = StatusEnum.valueOf(status.toLowerCase());
		} catch(IllegalArgumentException | NullPointerException npe) {
			throw new ApplicationException(MessageConstants.PROVIDED_STATUS_INVALID);
		}
		
		//logic
		String retStatus = shuttleService.updateShuttleBookingStatus(shuttleRequestId, statusEnum,reason);
		return new AppResponse<String>(getMessageFromStatusEnum(retStatus));
	}

	private String getMessageFromStatusEnum(String retStatus) {
		
		if(retStatus.toLowerCase().startsWith("approved")) {
			return MessageConstants.APPROVE_SUCCESS;
		} else if(retStatus.toLowerCase().startsWith("rejected")) {
			return MessageConstants.REJECT_SUCCESS;
		} else {
			return MessageConstants.CANCEL_SUCCESS;
		}
	}
}

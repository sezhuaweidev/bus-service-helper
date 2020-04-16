package app.infy.util.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.infy.util.exception.ApplicationException;
import app.infy.util.helper.AppResponse;
import app.infy.util.helper.LoginTypeEnum;
import app.infy.util.helper.MessageConstants;
import app.infy.util.model.LoginObject;
import app.infy.util.service.AuthenticationService;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
	
	private AuthenticationService authenticationService;
	
	public AuthenticationController(
			AuthenticationService authenticationService
			) {
		this.authenticationService = authenticationService;
	}
	
	@PostMapping(value="login",produces=MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public AppResponse<String> formLogin(@RequestBody @Valid LoginObject loginObject, BindingResult br) {
		if(br.hasErrors()) {
			throw new ApplicationException(MessageConstants.AUTHENTICATION_CREDENTIAL_INVALID,br.getAllErrors().parallelStream().map(t->t.getCode()).reduce((t1,t2)->(t1+", "+t2)).orElse(null));
		}
		
		String status = authenticationService.createSession(loginObject);
		
		return null;
	}
	
}

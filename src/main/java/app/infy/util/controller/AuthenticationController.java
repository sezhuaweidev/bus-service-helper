package app.infy.util.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.infy.util.exception.ApplicationException;
import app.infy.util.exception.UserAlreadyLoggedInException;
import app.infy.util.helper.AppResponse;
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
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!auth.getName().equalsIgnoreCase("anonymoususer")) {
			throw new UserAlreadyLoggedInException(MessageConstants.USER_ALREADY_LOGGED_IN);
		}
		
		System.out.println("formLogin called with "+loginObject.getUsername());
		String status = authenticationService.createSession(loginObject);
		
		return new AppResponse<String>(status);
	}
	
	@GetMapping(value="logout")
	public AppResponse<String> logoutUser(HttpServletRequest req, HttpServletResponse res) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null) {
			new SecurityContextLogoutHandler().logout(req, res, auth);
			return new AppResponse<String>("SUCCESS");
		} else {
			return new AppResponse<String>("UNKNOWN_ERROR");
		}
	}
	
}

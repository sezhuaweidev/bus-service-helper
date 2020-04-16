package app.infy.util.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import app.infy.util.entity.EmployeeAuth;
import app.infy.util.exception.ApplicationException;
import app.infy.util.exception.PasswordInvalidException;
import app.infy.util.exception.UsernameNotFoundException;
import app.infy.util.helper.LoginTypeEnum;
import app.infy.util.helper.MessageConstants;
import app.infy.util.model.LoginObject;
import app.infy.util.repository.EmployeeAuthRepository;
import app.infy.util.repository.EmployeePermissionRepository;
import app.infy.util.service.AuthenticationService;
import app.infy.util.service.ShuttleService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private BCryptPasswordEncoder passwordEncoder;
	private EmployeeAuthRepository empAuthRepo;
	private EmployeePermissionRepository empPermissionRepo;
	private ShuttleService shuttleService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public AuthenticationServiceImpl(EmployeeAuthRepository empAuthRepo, EmployeePermissionRepository empPermissionRepo,
			ShuttleService shuttleService, BCryptPasswordEncoder passwordEncoder ) {
		super();
		this.empAuthRepo = empAuthRepo;
		this.empPermissionRepo = empPermissionRepo;
		this.shuttleService = shuttleService;
		this.passwordEncoder = passwordEncoder;
	}



	@Override
	public String createSession(LoginObject loginObject) {
		EmployeeAuth ea = empAuthRepo.getUsernamePassword(loginObject.getUsername()).orElse(null);
		
		if(null == ea) {
			throw new UsernameNotFoundException(MessageConstants.USERNAME_NOT_FOUND);
		}
		if(!passwordEncoder.matches(loginObject.getPassword(), ea.getPassword()) ) {
			throw new PasswordInvalidException(MessageConstants.PASSWORD_IS_INVALID);
		}
		
		UsernamePasswordAuthenticationToken userPassAuthToken = new UsernamePasswordAuthenticationToken(loginObject.getUsername(), loginObject.getPassword());
		Authentication authentication = authenticationManager.authenticate(userPassAuthToken);
		System.out.println(authentication.getPrincipal());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "SUCCESS";
	}

}

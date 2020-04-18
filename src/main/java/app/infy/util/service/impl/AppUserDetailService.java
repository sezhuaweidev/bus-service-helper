package app.infy.util.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import app.infy.util.entity.EmployeeAuth;
import app.infy.util.helper.MessageConstants;
import app.infy.util.repository.EmployeeAuthRepository;
import app.infy.util.repository.EmployeePermissionRepository;

public class AppUserDetailService implements UserDetailsService {

	@Autowired
	private EmployeeAuthRepository empAuthRepo;

	@Autowired
	private EmployeePermissionRepository empPermRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("called with username:"+username);
		Integer intId = 0;
		
		intId = Integer.parseInt(username);
		
		System.out.println("integer id:"+intId);
		if(empAuthRepo.existsById(intId)) {
			EmployeeAuth ea = empAuthRepo.findById(intId).orElse(new EmployeeAuth());
			UserBuilder ub = User.withUsername(username);
			ub.password(ea.getPassword());
			List<GrantedAuthority> lstGrantedAuthority = empPermRepo.findByEmployeeId(username).stream().map(t->new SimpleGrantedAuthority(t.getEmpPermissionId())).collect(Collectors.toList());
			ub.authorities(lstGrantedAuthority);
			return ub.build();
		} else {
			throw new app.infy.util.exception.UsernameNotFoundException(MessageConstants.USERNAME_NOT_FOUND);
		} 
	}

}

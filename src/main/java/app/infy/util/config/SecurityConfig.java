package app.infy.util.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import app.infy.util.service.impl.AppUserDetailService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {	
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/static/**")
			.antMatchers("/templates/**")
			.antMatchers("/css/**")
			.antMatchers("/js/**")
			.antMatchers("/image/**")
			.antMatchers("/video/**");
			
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/auth/login").permitAll()
			.antMatchers("/shuttleservice/**", "/core/**", "/search/**","/view/**","/auth/logout").hasAnyAuthority("EMPLOYEE","MANAGER","TRANSPORT")
			.and()
			.formLogin()
			.loginPage("/view/login").permitAll()
			.and()
			.logout()
			.logoutUrl("/auth/logout").permitAll()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
			.invalidSessionUrl("/view/login")
			.and()
			.exceptionHandling().accessDeniedPage("/view/login");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(appUserDetailService()).passwordEncoder(passwordEncoder());
		//auth.inMemoryAuthentication()
		//.withUser("1030181").password(passwordEncoder().encode("Abcd@123")).roles("EMPLOYEE");
	}

	@Bean
	public UserDetailsService appUserDetailService() {
		return new AppUserDetailService();
	}
	
}

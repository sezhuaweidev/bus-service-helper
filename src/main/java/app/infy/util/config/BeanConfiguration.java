package app.infy.util.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import app.infy.util.helper.BeanService;
import app.infy.util.service.EmployeeService;
import app.infy.util.service.ShuttleService;

@Configuration
public class BeanConfiguration {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	ShuttleService shuttleService;

	@Bean(name = "beanService")
    public BeanService  beanService(){
        		return new BeanService(){
        			public String getEmployeeName(String empId) {
        				return employeeService.getEmployeeNameAndId(empId);
        			}

        		};
        		
    }


}

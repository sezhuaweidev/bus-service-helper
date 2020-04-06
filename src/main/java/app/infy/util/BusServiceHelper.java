package app.infy.util;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Status:</br>
 * -------------------</br>
 * Initial setup is almost complete.</br>
 * entity pojo added.</br> 
 * Confiuration based {@link AbstractRepository} initialized.</br>
 * Controller {@link BusServiceController} added. More endpoints can be added later. </br>
 * Service class Added. {@link ShuttleServiceImpl} no method implementation yet. <br/>
 * More data tables to be added. {@link EmployeeDetail} <br/>
 * <br/><br/>
 * About:</br>
 * ---------------</br>
 * 
 * @author Soham
 */
@SpringBootApplication
//disabling auto configuration of spring-data-jpa auto-configurations initially.
@EnableSwagger2
public class BusServiceHelper {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(BusServiceHelper.class, args);
	}
}

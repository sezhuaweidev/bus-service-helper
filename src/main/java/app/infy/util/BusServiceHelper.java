package app.infy.util;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Status:</br>
 * -------------------</br>
 * Initial setup is almost complete.</br>
 * entity pojo added.</br> 
 * Confiuration based {@link AbstractEmployeeRepository} initialized.</br>
 * Controller {@link BusServiceController} added. More endpoints can be added later. </br>
 * Service class Added. {@link ShuttleServiceImpl} no method implementation yet. <br/>
 * More data tables to be added. {@link EmployeeDetail} <br/>
 * <br/><br/>
 * About:</br>
 * ---------------</br>
 * Check bootstrap.properties to understand conditional bean arrangements.
 * Initially spring-data-jpa auto configuration is disabled.
 * Based on the value of ${module.employee.repository.enabled}, beans get intialized.<br/>
 * 
 * module.employee.repository.enabled = true means<br/>
 * {@link app.infy.util.config.DatasourceConfiguration} gets initialized.<br/>
 * {@code else}
 * {@link app.infy.util.config.RestRepositoryConfiguration} gets initialized.
 * 
 * @author Soham
 */
@SpringBootApplication
//disabling auto configuration of spring-data-jpa auto-configurations initially.
@EnableAutoConfiguration(
		exclude = { 
				DataSourceAutoConfiguration.class })
public class BusServiceHelper {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(BusServiceHelper.class, args);
	}
}

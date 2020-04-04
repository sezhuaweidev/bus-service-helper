package app.infy.util;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * Status:</br>
 * -------------------</br>
 * Initial setup is almost complete.</br>
 *entity pojo added.</br></br>
 * 
 * 
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
				DataSourceAutoConfiguration.class, 
				DataSourceTransactionManagerAutoConfiguration.class, 
				HibernateJpaAutoConfiguration.class })
public class BusServiceHelper {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(BusServiceHelper.class, args);
	}
}

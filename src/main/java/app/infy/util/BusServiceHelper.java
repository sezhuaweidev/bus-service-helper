package app.infy.util;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * Initial setup is almost complete.</br>
 * Need to add entity pojos.
 * Check bootstrap.properties to understand conditional bean arrangements.
 * Initially spring-data-jpa auto configuration is disabled.</br>
 * Based on the value of ${module.employee.repository.enabled}, beans get intialized.
 * @author Soham
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
public class BusServiceHelper {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(BusServiceHelper.class, args);
	}
}

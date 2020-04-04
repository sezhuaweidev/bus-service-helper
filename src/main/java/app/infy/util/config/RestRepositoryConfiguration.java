package app.infy.util.config;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import app.infy.util.entity.EmployeeDetail;
import app.infy.util.repository.AbstractEmployeeRepository;
import app.infy.util.repository.impl.EmployeeRestRepository;

@Configuration
@ConditionalOnProperty(
		value = "module.employee.repository.enabled",
		havingValue = "false",
		matchIfMissing = true
		)
@ConfigurationProperties(value = "")
public class RestRepositoryConfiguration {

	@Value("${module.employee.repository.rest.uri}")
	@NotNull
	private String repositoryRestUri;
	
	@Value("${module.employee.repository.rest.identifier.token}")
	@NotNull
	private String repositoryRestToken;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public  AbstractEmployeeRepository<EmployeeDetail, Integer> employeeRepository() {
		System.out.println("EmployeeRestRepository called");
		return new EmployeeRestRepository(repositoryRestUri, repositoryRestToken);
	}
}

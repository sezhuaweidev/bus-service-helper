package app.infy.util.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import app.infy.util.repository.EmployeeRestRepository;

@Configuration
@ConditionalOnProperty(
		value = "module.employee.repository.enabled",
		havingValue = "false",
		matchIfMissing = true
		)
@ConfigurationProperties(value = "")
public class RestRepositoryConfiguration {

	@Value("${module.employee.repository.rest.uri}")
	private String repositoryRestUri;
	
	@Value("${module.employee.repository.rest.identifier.token}")
	private String repositoryRestToken;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public EmployeeRestRepository employeeRepository() {
		System.out.println("EmployeeRepository called");
		return new EmployeeRestRepository(repositoryRestUri, repositoryRestToken);
	}
}

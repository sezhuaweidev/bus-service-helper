package app.infy.util.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ConditionalOnProperty(
		value = "module.employee.repository.enabled",
		havingValue = "true",
		matchIfMissing = true
		)
public class DatasourceConfiguration {
	
	@Value("${module.employee.repository.datasource.url}")
	private String datasourceUrl;
	
	@Value("${module.employee.repository.datasource.driverClassName}")
	private String datasourceDriverClassName;
	
	@Value("${module.employee.repository.datasource.username}")
	private String datasourceUsername;
	
	@Value("${module.employee.repository.datasource.password}")
	private String datasourcePassword;
	
	@Bean
	public HikariDataSource dataSource() {
		System.out.println("About to configure datasource");
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(datasourceDriverClassName);
		hikariConfig.setJdbcUrl(datasourceUrl);
		hikariConfig.setUsername(datasourceUsername);
		hikariConfig.setPassword(datasourcePassword);
		return new HikariDataSource(hikariConfig);
	}
	
}

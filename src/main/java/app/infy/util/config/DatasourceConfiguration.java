package app.infy.util.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import app.infy.util.entity.EmployeeDetail;
import app.infy.util.repository.AbstractEmployeeRepository;
import app.infy.util.repository.impl.EmployeeDbRepository;


/**
 * This is the Datasource configuration & repository setup class.
 * <br/>
 * It is a conditional bean.<br/>
 * Datasource properties are first read from properties file.
 * Then the HikariDataSource is configured. Hikari is a connection pooling framework.
 * Then our {@link AbstractEmployeeRepository} is initialized
 * with {@link aEmployeeDbRepository} implementation.
 * @author Soham
 */
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
	
	/**
	 * Datasource configuration
	 * @return {@link HikariDataSource} as {@link DataSource}
	 */
	@Bean
	public DataSource dataSource() {
		System.out.println("About to configure datasource");
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(datasourceDriverClassName);
		hikariConfig.setJdbcUrl(datasourceUrl);
		hikariConfig.setUsername(datasourceUsername);
		hikariConfig.setPassword(datasourcePassword);
		return new HikariDataSource(hikariConfig);
	}

	/**
	 * Creation of {@link EmployeeDbRepository} as an {@link AbstractEmployeeRepository}
	 * @param dataSource  -  Just to make sure its initialized. No need in this method.
	 * @param jdbcTemplate - {@link EmployeeDbRepository} uses jdbcTemplate to execute queries.
	 * @return {@link AbstractEmployeeRepository}
	 */
	@Bean
	public AbstractEmployeeRepository<EmployeeDetail, Integer> employeeDbRepository(DataSource dataSource, JdbcTemplate jdbcTemplate) {
		System.out.println("EmployeeDBRepository called.");
		return new EmployeeDbRepository(jdbcTemplate);
	}
	
	//@Bean
	/**
	public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		localContainerEntityManagerFactoryBean.setDataSource(dataSource);
		localContainerEntityManagerFactoryBean.setPackagesToScan("app.infy.util.entity");
		
		Map<String, String> jpaProperties = new HashMap<>();
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		jpaProperties.put("hibernate.show_sql","true");
		jpaProperties.put("hibernate.format_sql","true");
		jpaProperties.put("hibernate.hbm2ddl.auto", "validate");
		
		localContainerEntityManagerFactoryBean.setJpaPropertyMap(jpaProperties);
		return localContainerEntityManagerFactoryBean;
	}
	**/
	
	/**
	//@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
		return jpaTransactionManager;
	}
	**/
}

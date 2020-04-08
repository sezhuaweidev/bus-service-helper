package app.infy.util;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Status:</br>
 * -------------------</br>
 * Initial setup is almost complete.</br>
 * entity pojo added.</br> 
 * Controller {@link BusServiceController} added. More endpoints can be added later. </br>
 * Service class Added. {@link ShuttleServiceImpl} no method implementation yet. <br/>
 * Data tables added. {@link EmployeeDetail} <br/>
 * <br/><br/>
 * About:</br>
 * ---------------</br>
 * http://localhost:8082/api/shuttleservice/ - POST - request status - returns newly created resource get url <br />
 * http://localhost:8082/api/shuttleservice/{shuttleRequestId} - GET - request status - returns {@link ShuttleBookingStatus} object <br />
 * http://localhost:8082/api/shuttleservice/{shuttleRequestId}/{approved or rejected or cancelled}  <br />
 * 		- PUT - update request status - returns SUCCESS or {@link MessageConstants.EMAIL_FAILED_BUT_APPROVED} <br />
 * 
 * http://localhost:8082/api/core/infydc - GET - dc list <br />
 * http://localhost:8082/api/core/shuttletiming - GET - shuttle timing list <br />
 * @author Soham
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableSwagger2
public class BusServiceHelper extends SpringBootServletInitializer {
	
	//this is where the newly created shuttle requests are stored.
	public static ConcurrentHashMap<String, Long> APPROVAL_MAP = new ConcurrentHashMap<>();
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(BusServiceHelper.class, args);
	}
	
	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager("shuttletiming","infydc","infyregion","infycountry");
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BusServiceHelper.class);
	}
	
}

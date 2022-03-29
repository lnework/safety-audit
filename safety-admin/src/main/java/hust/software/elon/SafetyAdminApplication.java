package hust.software.elon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;


@ImportResource(locations = {"classpath:spring*.xml"})
@SpringBootApplication
public class SafetyAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyAdminApplication.class, args);
	}

}

package hust.software.elon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = {"classpath:spring*.xml"})
@SpringBootApplication
public class SafetyRiskApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyRiskApplication.class, args);
	}

}

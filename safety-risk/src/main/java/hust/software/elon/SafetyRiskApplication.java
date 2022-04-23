package hust.software.elon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@MapperScan("hust.software.elon.mapper")
@ImportResource(locations = {"classpath:spring*.xml"})
@SpringBootApplication
public class SafetyRiskApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyRiskApplication.class, args);
	}

}

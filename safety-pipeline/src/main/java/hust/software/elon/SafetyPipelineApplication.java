package hust.software.elon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


@ImportResource(locations = {"classpath:spring*.xml"})
@MapperScan("hust.software.elon.mapper")
@SpringBootApplication
public class SafetyPipelineApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyPipelineApplication.class, args);
	}

}

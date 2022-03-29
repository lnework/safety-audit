package hust.software.elon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;


@ImportResource(locations = {"classpath:spring*.xml"})
@MapperScan("hust.software.elon.mapper")
@SpringBootApplication
public class SafetyMiddleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyMiddleApplication.class, args);
	}

}

package lab.microservice.currencyconversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringGreetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringGreetApplication.class, args);
	}

}

package lab.microservice.userrepo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient

public class SpringBootUserRepoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUserRepoApplication.class, args);
	}
	
}

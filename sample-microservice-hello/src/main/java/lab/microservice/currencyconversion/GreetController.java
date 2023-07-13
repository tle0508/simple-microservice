package lab.microservice.currencyconversion;


import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepoServiceProxy proxy;

	@GetMapping("/api/greet/{id}")
	public ResponseEntity<UserMessageBean> greet(@PathVariable Long id) {

		// call userrepo service
		UserMessageBean message = proxy.retrieveUsername(id);

		LocalTime currentTime = LocalTime.now();
		String greeting = "Hello!";
		if (currentTime.isBefore(LocalTime.NOON)) {
            greeting = "Good morning!";
        } else if (currentTime.isBefore(LocalTime.of(17, 0))) {
			greeting = "Good afternoon!";
        } else {
			greeting = "Good evening!";
        }
		message.setMessage(greeting + " " + message.getName() + "!");
		logger.info("RESPONSE: " + greeting);

		return ResponseEntity.ok(message);
	}

}
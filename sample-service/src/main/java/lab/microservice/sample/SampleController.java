package lab.microservice.sample;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
   
    // get request for hello world with response body ok
    @RequestMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
    }
    
}

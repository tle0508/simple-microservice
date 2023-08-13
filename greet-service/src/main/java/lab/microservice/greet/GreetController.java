package lab.microservice.greet;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    @Autowired
    private UserServiceProxy userServiceProxy;
   
    // add service to greet user by id
    @RequestMapping("/greet/{id}")
    public ResponseEntity<String> greetUserById(@PathVariable Long id) {
        // call user-service to get user by id
        UserDTO userDTO = userServiceProxy.getUser(id);

        // return hello with username
        return new ResponseEntity<String>("Hello " + userDTO.getUsername() +" at port:"+userDTO.getPort(), HttpStatus.OK);

    }

    
}

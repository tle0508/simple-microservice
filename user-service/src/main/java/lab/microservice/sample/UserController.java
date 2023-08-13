package lab.microservice.sample;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
   
    // get request to get user by id
    @RequestMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> optUser = userRepository.findById(id);
        if (!optUser.isPresent()) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        User user = optUser.get();
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}

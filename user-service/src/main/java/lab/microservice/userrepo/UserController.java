package lab.microservice.userrepo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Environment environment;

    // get request to get user by id
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> optUser = userRepository.findById(id);
        if (!optUser.isPresent()) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        User user = optUser.get();
        user.setPort(Integer.parseInt(environment.getProperty("server.port")));
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}

package ch.uzh.ifi.seal.soprafs19.controller;

import ch.uzh.ifi.seal.soprafs19.entity.User;
import ch.uzh.ifi.seal.soprafs19.repository.UserRepository;
import ch.uzh.ifi.seal.soprafs19.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


//not here
@RestController
public class UserController {

    private final UserService service;

    UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    Iterable<User> all() {
        return service.getUsers();
    }

    @PostMapping("/users")
    User createUser(@RequestBody User newUser) {
        return this.service.createUser(newUser);
    }

    @PostMapping("/users/login")
    User loginUser(@RequestBody User someUser) {return this.service.loginUser(someUser);}

    @GetMapping("/users/{username}")
    ResponseEntity userByUsername(@PathVariable String username) {

        User prof = service.getUserbyUname(username);
        if (prof != null)
            return ResponseEntity.status(HttpStatus.OK).body(prof);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no user with username " + username + " .");
    }

}

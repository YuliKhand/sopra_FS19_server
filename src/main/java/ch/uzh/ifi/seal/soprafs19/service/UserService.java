package ch.uzh.ifi.seal.soprafs19.service;

import ch.uzh.ifi.seal.soprafs19.constant.UserStatus;
import ch.uzh.ifi.seal.soprafs19.entity.User;
import ch.uzh.ifi.seal.soprafs19.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ch.uzh.ifi.seal.soprafs19.exception.UsernameAlreadyExistsException;
import ch.uzh.ifi.seal.soprafs19.exception.UserNotFoundException;
import ch.uzh.ifi.seal.soprafs19.exception.WrongPasswordException;
import java.sql.Timestamp;
//not here
import java.util.UUID;

@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User createUser(User newUser) {
        User element = this.userRepository.findByUsername(newUser.getUsername());
        if (element!=null){
            throw new UsernameAlreadyExistsException(element.getUsername());
        }
        newUser.setToken(UUID.randomUUID().toString());
        newUser.setStatus(UserStatus.ONLINE);
        newUser.setTimestamp(new Timestamp(System.currentTimeMillis()));
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public User loginUser(User someUser) {
        User element = this.userRepository.findByUsername(someUser.getUsername());
        if (element == null){
            throw new UserNotFoundException(someUser.getUsername());
        } else if (!(element.getPassword().equals(someUser.getPassword()))) {
            throw new WrongPasswordException();
        } else {
            return someUser;
        }
    }
}

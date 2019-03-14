package ch.uzh.ifi.seal.soprafs19.repository;

import ch.uzh.ifi.seal.soprafs19.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//not here
@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
	User findByPassword(String password);
	User findByToken(String token);
}

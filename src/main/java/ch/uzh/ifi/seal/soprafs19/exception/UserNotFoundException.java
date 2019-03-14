package ch.uzh.ifi.seal.soprafs19.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String username) { super("The username " + username + " does not exist.");}
}

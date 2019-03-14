package ch.uzh.ifi.seal.soprafs19.exception;

public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException(String username) {
        super("The username " + username + " is already taken.");
    }
}

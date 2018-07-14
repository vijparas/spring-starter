package in.vnl.spring.exceptions.validation.user;

public class UsernameNotUniqueException extends Exception {
	public UsernameNotUniqueException(String username) {
		super(username+" Has Already Been Taken");
	}
}

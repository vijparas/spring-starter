package in.vnl.spring.exceptions.validation.user;

public class UsernameNotUniqueException extends Exception {
	public UsernameNotUniqueException(String username) {
		super(username+" Username Has Already Been Taken");
	}
}

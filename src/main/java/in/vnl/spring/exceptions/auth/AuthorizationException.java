package in.vnl.spring.exceptions.auth;

public class AuthorizationException extends Exception {

	public AuthorizationException() {
		super("User is not given access");
	}
}

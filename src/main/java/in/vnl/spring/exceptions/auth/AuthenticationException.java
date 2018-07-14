package in.vnl.spring.exceptions.auth;

public class AuthenticationException extends Exception {
	
	public AuthenticationException() {
		super("User Is Not Logged In");
	}
	
}

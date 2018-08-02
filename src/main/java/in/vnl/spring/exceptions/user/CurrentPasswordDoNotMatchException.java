package in.vnl.spring.exceptions.user;

public class CurrentPasswordDoNotMatchException extends Exception {

	public CurrentPasswordDoNotMatchException() {
		super("Current Password Is Incorrect");
	}
}

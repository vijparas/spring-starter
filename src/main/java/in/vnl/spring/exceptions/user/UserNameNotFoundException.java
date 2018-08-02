package in.vnl.spring.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNameNotFoundException extends RuntimeException {

	public UserNameNotFoundException(String username) {
		super("Username "+username+" Does Not Exist");
	}
}

package in.vnl.spring.exceptions.role;

public class RoleNameDoesNotExistException extends Exception {

	public RoleNameDoesNotExistException(String roleName) {
		super(roleName+" Does Not Exist");
	}
}

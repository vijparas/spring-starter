package in.vnl.spring.exceptions.validation.role;

public class RoleNameNotUniqueException extends Exception {

	public RoleNameNotUniqueException(String roleName) {
		super(roleName+" Is Already Present");
	}
}

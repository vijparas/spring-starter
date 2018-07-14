package in.vnl.spring.authorization;

import in.vnl.spring.exceptions.auth.AuthorizationException;
import in.vnl.spring.exceptions.auth.AuthenticationException;

public interface CheckAuthorization {

	public void checkAuthentication() throws AuthenticationException;
	public boolean checkAuthorization(String[] roles) throws AuthorizationException;
	public boolean checkAuthorization(String[] roles,String username) throws AuthorizationException;
}

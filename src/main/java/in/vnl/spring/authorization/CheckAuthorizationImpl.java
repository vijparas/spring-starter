package in.vnl.spring.authorization;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import in.vnl.spring.entity.UserEntity;
import in.vnl.spring.exceptions.auth.AuthenticationException;
import in.vnl.spring.exceptions.auth.AuthorizationException;

@Component
public class CheckAuthorizationImpl implements CheckAuthorization {

	@Override
	public boolean checkAuthorization(String[] allowedRoles) throws AuthorizationException {
		try {
			UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Set<String> allowedRolesSet = new HashSet<>(Arrays.asList(allowedRoles));
			Set<String> roles = user.getRolesArray();
			Set<String> intersection = new HashSet<String>(allowedRolesSet); // use the copy constructor
			intersection.retainAll(roles);
			
			if(allowedRolesSet.contains("Self")) {
				
			}
			
			if (intersection.size() == 0) {
				throw new AuthorizationException();
			}
			
			

		} catch (AuthorizationException exception) {
			throw exception;
		} catch (Exception exception) {

		}
		return true;
	}

	@Override
	public void checkAuthentication() throws AuthenticationException {
		
		try {
			if(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
			
				throw new AuthenticationException(); 
			}
			
		
		}

		catch (NullPointerException exception) {
			throw new AuthenticationException();
		}

	}

	@Override
	public boolean checkAuthorization(String[] allowedRoles, String username) throws AuthorizationException {
		try {
			UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Set<String> allowedRolesSet = new HashSet<>(Arrays.asList(allowedRoles));
			Set<String> roles = user.getRolesArray();
			Set<String> intersection = new HashSet<String>(allowedRolesSet); // use the copy constructor
			intersection.retainAll(roles);
			if (intersection.size() == 0) {
				
				if(allowedRolesSet.contains("Self")) {
					if(!authroizeUser(username)) {
						throw new AuthorizationException();
					}
				}
				
			}
			return true;
		}
		
		catch(AuthorizationException exception) {
			throw exception;
		}
		
	}
	
	
	private  boolean authroizeUser(String username) {
		UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(username.equalsIgnoreCase(user.getUsername())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	

}

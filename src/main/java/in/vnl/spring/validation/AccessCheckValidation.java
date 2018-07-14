package in.vnl.spring.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import in.vnl.spring.annotations.LoginRequired;
import in.vnl.spring.exceptions.auth.AuthorizationException;


public class AccessCheckValidation implements ConstraintValidator<LoginRequired, String[]> {

	@Override
	public boolean isValid(String[] roles, ConstraintValidatorContext arg1)  {
		for(String role:roles) {
			if(role.equals("Admin")) {
				return true;
			}
		}
		return false;
		
	}

}

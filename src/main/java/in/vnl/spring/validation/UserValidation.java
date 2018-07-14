package in.vnl.spring.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.vnl.spring.entity.BaseEntity;
import in.vnl.spring.entity.RoleEntity;
import in.vnl.spring.entity.UserEntity;
import in.vnl.spring.entity.pojo.BasePojo;
import in.vnl.spring.entity.pojo.user.UserPojo;
import in.vnl.spring.entity.pojo.user.UserUpdatePojo;
import in.vnl.spring.exceptions.validation.user.UsernameNotUniqueException;
import in.vnl.spring.repository.UserRepository;
import in.vnl.spring.service.UserService;

@Component
public class UserValidation  {
	
	@Autowired
	private UserRepository userRepository;
	
	public void create(BasePojo basePojo) throws UsernameNotUniqueException {
		try {
			UserPojo userPojo=(UserPojo)basePojo;
			this.checkUsername(userPojo.getUsername());
		}
		catch (UsernameNotUniqueException exception) {
			throw exception;
		}
		
	}

	
	public void update(UserUpdatePojo baseEntity) {
		// TODO Auto-generated method stub
		
	}

	
	public void delete(BasePojo baseEntity) {
		// TODO Auto-generated method stub
		
	}
	
	private void checkUsername(String username) throws UsernameNotUniqueException {
		try {
			Optional<UserEntity> user=userRepository.findByUsername(username);
			if(user.isPresent()) {
				throw new UsernameNotUniqueException(username);
			}
		}
		catch(UsernameNotUniqueException exception) {
			throw exception;
		}
		catch (Exception exception) {
			// TODO: handle exception
		}
	}

	

}

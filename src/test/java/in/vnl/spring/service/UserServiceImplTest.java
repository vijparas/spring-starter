package in.vnl.spring.service;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import in.vnl.spring.converters.user.UserEntityToUserPojo;
import in.vnl.spring.converters.user.UserPojoToUserEntity;
import in.vnl.spring.converters.user.UserUpdatePojoToUserEntity;
import in.vnl.spring.entity.UserEntity;
import in.vnl.spring.entity.pojo.user.UserUpdatePojo;
import in.vnl.spring.exceptions.user.UserNameNotFoundException;
import in.vnl.spring.repository.UserRepository;
import in.vnl.spring.validation.UserValidation;
@ActiveProfiles("test")
public class UserServiceImplTest {

	@Mock
	private UserValidation userValidation;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private UserPojoToUserEntity userPojoToUserEntityConverter;
	
	@Mock
	private UserEntityToUserPojo userEntityToUserPojoConverter;
	
	@Mock
	private UserUpdatePojoToUserEntity userUpdateToUserEntityConverter;
	
	private UserService userService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		userService=new UserServiceImpl(userValidation, userRepository, userPojoToUserEntityConverter, userEntityToUserPojoConverter, userUpdateToUserEntityConverter);
	}
	
	@Test
	public void userUpdate() {
		UserUpdatePojo userUpdate=new UserUpdatePojo();
		
	}
	@Test(expected=UserNameNotFoundException.class)
	public void testUserNameNotFoundException() {
		Optional<UserEntity> user=Optional.empty();
		when(userRepository.findByUsername(anyString())).thenReturn(user);
		userService.getUserByUsername(anyString());
		
	}
	
}

package in.vnl.spring.service;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;import in.vnl.spring.Spring;
import in.vnl.spring.controller.UserController;
import in.vnl.spring.converters.user.UserEntityToUserPojo;
import in.vnl.spring.converters.user.UserPojoToUserEntity;
import in.vnl.spring.entity.pojo.user.UserPojo;
import in.vnl.spring.exceptions.validation.user.UsernameNotUniqueException;
import in.vnl.spring.repository.UserRepository;
import in.vnl.spring.validation.UserValidation;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@SpringBootConfiguration()
public class UserServiceIT {

//	@Autowired
//	private UserRepository userRepository;
//	
//	@Autowired
//	
//	private UserService userService;
//	
//	@Autowired
//	private UserValidation userValidation;
//	
//	@Autowired
//	private UserPojoToUserEntity userPojoToUserEntity;
//	
//	@Autowired
//	private UserEntityToUserPojo userEntityToUserPojo;
////	@Before
////	public void setUp() throws Exception {
////		MockitoAnnotations.initMocks(this);
////
////		userService = new UserServiceImpl(userValidation, userRepository, userPojoToUserEntity, userEntityToUserPojo);
////		
////	}
//	@Transactional
//	@Test(expected = UsernameNotUniqueException.class)
//	
//	public void checkUserNameNotUniqueException() throws UsernameNotUniqueException {
//		UserPojo userPojo=new UserPojo();
//		userPojo.setActive(1);
//		userPojo.setUsername("admin");
//		userPojo.setEmail("vijparas@gmail.com");
//		userService.create(userPojo);
//		
//		
//	}
}

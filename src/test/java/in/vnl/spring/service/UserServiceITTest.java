package in.vnl.spring.service;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import in.vnl.spring.entity.pojo.user.UserPojo;
import in.vnl.spring.exceptions.user.UserNameNotFoundException;
import in.vnl.spring.exceptions.validation.user.UsernameNotUniqueException;

@RunWith(SpringRunner.class)
@SpringBootTest()
@TestPropertySource(locations = "classpath:application-test.yml")
@ActiveProfiles("test")
public class UserServiceITTest {

	@Autowired
	private UserService userService;

	@Transactional
	@Test(expected = UsernameNotUniqueException.class)

	public void checkUserNameNotUniqueException() throws UsernameNotUniqueException {
		UserPojo userPojo = new UserPojo();
		userPojo.setActive("Active");
		userPojo.setUsername("admin");
		userPojo.setEmail("vijparas@gmail.com");
		userService.create(userPojo);

	}

	@Transactional
	@Test
	public void createUser() throws UsernameNotUniqueException {
		UserPojo userPojo = new UserPojo();
		userPojo.setActive("Active");
		userPojo.setUsername("admin2");
		userPojo.setEmail("vijparas23@gmail.com");
		userPojo.setPassword("220386");
		userPojo.setConfirmPassword("220386");
		userPojo.setFirstName("Paras");
		userPojo.setLastName("Vij");
		userPojo.setMobile("8725840873");

		UserPojo createdUserPojo = userService.create(userPojo);
		assertEquals("Active", createdUserPojo.getActive());
	}

	@Transactional
	@Test
	public void deleteUser() throws UserNameNotFoundException {
		UserPojo userPojo = userService.getUserByUsername("admin");
		userService.delete(userPojo);
	}

	@Transactional
	@Test
	public void checkUsername() throws UserNameNotFoundException {
		UserPojo userPojo = userService.getUserByUsername("admin");
		assertEquals("Admin", userPojo.getFirstName());
	}

	@Transactional
	@Test(expected = UserNameNotFoundException.class)
	public void invalidUsername() throws UserNameNotFoundException {
		userService.getUserByUsername("admakmsa");
	}
}

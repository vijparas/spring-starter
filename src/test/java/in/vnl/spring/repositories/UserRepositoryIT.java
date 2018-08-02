package in.vnl.spring.repositories;


import static org.junit.Assert.assertEquals;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import in.vnl.spring.Spring;
import in.vnl.spring.entity.UserEntity;
import in.vnl.spring.entity.pojo.user.UserPojo;
import in.vnl.spring.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")

public class UserRepositoryIT {

	@Autowired
	private UserRepository userRepository;
	
	@Before
	public void setup() throws Exception{
		
	}
	
	@Test
	@Transactional
	public void findById() {
		long id=1l;
		Optional<UserEntity> userEntity=userRepository.findById(id);
		assertEquals("vijparas@gmail.com", userEntity.get().getEmail());
		assertEquals("Paras", userEntity.get().getFirstName());
	}
	
	
	
}

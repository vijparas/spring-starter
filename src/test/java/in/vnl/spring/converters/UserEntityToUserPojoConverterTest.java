package in.vnl.spring.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import in.vnl.spring.converters.user.UserEntityToUserPojo;
import in.vnl.spring.entity.UserEntity;
import in.vnl.spring.entity.pojo.user.UserPojo;
@ActiveProfiles("test")
public class UserEntityToUserPojoConverterTest {

	public static final long ID_VALUE = new Long(1L);
	
	UserEntityToUserPojo userEntityToUserPojo;
	
	@Before
	public void setup() throws Exception{
		userEntityToUserPojo=new UserEntityToUserPojo();
	}
	@Test
	public void testNullObject() throws Exception{
		
		assertNull(userEntityToUserPojo.convert(null));
	}
	
	@Test
	public void testNotNullObject() throws Exception{
		UserEntity userEntity=new UserEntity();
		assertNotNull(userEntityToUserPojo.convert(userEntity));
	}
	
	@Test
	public void convert() throws Exception{
		UserEntity userEntity=new UserEntity();
		userEntity.setId(ID_VALUE);
		UserPojo userPojo=userEntityToUserPojo.convert(userEntity);
		assertEquals(1, userPojo.getId());
	}
}

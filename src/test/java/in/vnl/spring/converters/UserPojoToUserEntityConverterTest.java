package in.vnl.spring.converters;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;

import in.vnl.spring.converters.role.RolePojoToRoleEntity;
import in.vnl.spring.converters.user.UserPojoToUserEntity;
import in.vnl.spring.entity.UserEntity;
import in.vnl.spring.entity.pojo.user.UserPojo;
import in.vnl.spring.service.RoleService;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
@ActiveProfiles("test")
public class UserPojoToUserEntityConverterTest {

	public static final long ID_VALUE = new Long(1L);
	UserPojoToUserEntity converter;
	@Mock
	private RoleService roleService;
	
	@Mock
	private RolePojoToRoleEntity rolePojoToRoleEntity;
	@Before
	public void setup() {
		converter=new UserPojoToUserEntity(roleService,rolePojoToRoleEntity);
	}
	
	@Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }
	
	@Test
	public void testNotNullObject() throws Exception{
		UserPojo userPojo=new UserPojo();
		assertNotNull(userPojo);
	}
	
	@Test
	
	public void testConvert() throws Exception{
		
		UserPojo userPojo=new UserPojo();
		userPojo.setId(ID_VALUE);
		
		
		UserEntity userEntity=converter.convert(userPojo);
		assertEquals(ID_VALUE, userEntity.getId());
		
	}
	
}

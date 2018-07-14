package in.vnl.spring.converters.user;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import in.vnl.spring.converters.role.RolePojoToRoleEntity;
import in.vnl.spring.entity.UserEntity;
import in.vnl.spring.entity.pojo.role.RolePojo;
import in.vnl.spring.entity.pojo.user.UserPojo;
import in.vnl.spring.service.RoleService;

@Component
public class UserPojoToUserEntity implements Converter<UserPojo, UserEntity> {

	
	private RoleService roleService;
	private RolePojoToRoleEntity rolePojoToRoleEntityConveter;
	
	public UserPojoToUserEntity(RoleService roleService, RolePojoToRoleEntity rolePojoToRoleEntityConveter) {
		super();
		this.roleService = roleService;
		this.rolePojoToRoleEntityConveter = rolePojoToRoleEntityConveter;
	}





	@Override
	public UserEntity convert(UserPojo userPojo) {
		if(userPojo==null) {
			return null;
		}
		UserEntity userEntity=new UserEntity();
		
		try {
			userEntity.setId(userPojo.getId());
			userEntity.setActive(userPojo.getActive());
			userEntity.setPassword(userPojo.getPassword());
			userEntity.setUsername(userPojo.getUsername());
			userEntity.setCurrentPassword(userPojo.getCurrentPassword());
			userEntity.setConfirmPassword(userPojo.getConfirmPassword());
			userEntity.setEmail(userPojo.getEmail());
			userEntity.setFirstName(userPojo.getFirstName());
			userEntity.setLastName(userPojo.getLastName());
			userEntity.setMobile(userPojo.getMobile());
			
			
			for (Long roleId:userPojo.getRoles()) {
				
				RolePojo rolePojo=roleService.getRole(roleId);
				userEntity.addRole(rolePojoToRoleEntityConveter.convert(rolePojo));
			}
			
		}
		catch(Exception exception) {
			System.out.println(exception.getMessage());
		}
		return userEntity;
	}

	
}

package in.vnl.spring.converters.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import in.vnl.spring.converters.role.RoleEntityToRolePojo;
import in.vnl.spring.entity.RoleEntity;
import in.vnl.spring.entity.UserEntity;
import in.vnl.spring.entity.pojo.role.RolePojo;
import in.vnl.spring.entity.pojo.user.UserPojo;

@Component
public class UserEntityToUserPojo implements Converter<UserEntity, UserPojo> {

	@Autowired
	private RoleEntityToRolePojo roleEntityToRolePojo;
	@Override
	public UserPojo convert(UserEntity userEntity) {
		if(userEntity==null) {
			return null;
		}
		UserPojo userPojo=new UserPojo();
		try {
			
			userPojo.setActive(userEntity.getActive());
			userPojo.setCreatedDateTime(userEntity.getCreatedDateTime());
			userPojo.setCurrentPassword(userEntity.getCurrentPassword());
			userPojo.setEmail(userEntity.getEmail());
			userPojo.setFirstName(userEntity.getFirstName());
			userPojo.setId(userEntity.getId());
			userPojo.setLastName(userEntity.getLastName());
			userPojo.setMobile(userEntity.getMobile());
			userPojo.setPassword(userEntity.getPassword());
			userPojo.setUsername(userEntity.getUsername());
			List<Long> roles=new ArrayList<>();
			List<RolePojo> rolePojo=new ArrayList<>();
			for(RoleEntity roleEntity:userEntity.getRoles()) {
				roles.add(roleEntity.getId());
				rolePojo.add(roleEntityToRolePojo.convert(roleEntity));
			}
			userPojo.setRolePojo(rolePojo);
			userPojo.setRoles(roles);
		}
		catch(Exception exception) {
			System.out.println(exception.getMessage());
		}
		return userPojo;
	}

	
}

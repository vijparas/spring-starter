package in.vnl.spring.converters.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import in.vnl.spring.converters.role.RolePojoToRoleEntity;
import in.vnl.spring.entity.UserEntity;
import in.vnl.spring.entity.pojo.role.RolePojo;
import in.vnl.spring.entity.pojo.user.UserUpdatePojo;
import in.vnl.spring.repository.UserRepository;
import in.vnl.spring.service.RoleService;

@Component
public class UserUpdatePojoToUserEntity implements Converter<UserUpdatePojo, UserEntity> {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleService roleService;

	@Autowired
	private RolePojoToRoleEntity rolePojoToRoleEntityConveter;

	@Override
	public UserEntity convert(UserUpdatePojo userPojo) {
		try {
			UserEntity userEntity = userRepository.findByUsername(userPojo.getUsername()).get();
			userEntity.setActive(userPojo.getActive());

			userEntity.setUsername(userPojo.getUsername());
			userEntity.setEmail(userPojo.getEmail());
			userEntity.setFirstName(userPojo.getFirstName());
			userEntity.setLastName(userPojo.getLastName());
			userEntity.setMobile(userPojo.getMobile());

			for (Long roleId : userPojo.getRoles()) {

				RolePojo rolePojo = roleService.getRole(roleId);
				userEntity.addRole(rolePojoToRoleEntityConveter.convert(rolePojo));
			}
			return userEntity;

		}

		catch (Exception exception) {
			throw exception;
		}

	}

}

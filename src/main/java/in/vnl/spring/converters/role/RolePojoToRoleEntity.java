package in.vnl.spring.converters.role;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import in.vnl.spring.entity.RoleEntity;
import in.vnl.spring.entity.pojo.role.RolePojo;

@Component
public class RolePojoToRoleEntity implements Converter<RolePojo, RoleEntity> {

	@Override
	public RoleEntity convert(RolePojo rolePojo) {
		if(rolePojo==null) {
			return null;
		}
		RoleEntity roleEntity=new RoleEntity();
		roleEntity.setActive(rolePojo.getActive());
		roleEntity.setId(rolePojo.getId());
		roleEntity.setRole(rolePojo.getRole());
		return roleEntity;
	}
	
	public List<RoleEntity> convertAllRoles(List<RolePojo> roles){
		List<RoleEntity> roleEntities=new ArrayList<>();
		try {
			for(RolePojo role:roles) {
				RoleEntity roleEntity=new RoleEntity();
				roleEntity.setActive(role.getActive());
				roleEntity.setRole(role.getRole());
				roleEntity.setId(role.getId());
				roleEntities.add(roleEntity);
			}
		}
		catch(Exception exception) {
			throw exception;
		}
		return roleEntities;
	}

	
}

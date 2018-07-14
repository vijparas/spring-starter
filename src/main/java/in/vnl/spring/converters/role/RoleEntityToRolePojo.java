package in.vnl.spring.converters.role;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import in.vnl.spring.entity.RoleEntity;
import in.vnl.spring.entity.pojo.role.RolePojo;

@Component
public class RoleEntityToRolePojo implements Converter<RoleEntity, RolePojo> {

	@Override
	public RolePojo convert(RoleEntity roleEntity) {
		
		if(roleEntity==null) {
			
			return null;
		}
		RolePojo rolePojo=new RolePojo();
		rolePojo.setActive(roleEntity.getActive());
		rolePojo.setRole(roleEntity.getRole());
		rolePojo.setId(roleEntity.getId());
		rolePojo.setCreatedDateTime(roleEntity.getCreatedDateTime());
		rolePojo.setUpdatedDateTime(roleEntity.getUpdatedDateTime());
		
		return rolePojo;
	}
	
	public List<RolePojo> convertAllRoles(List<RoleEntity> roles){
		List<RolePojo> rolePojos=new ArrayList<>();
		try {
			
			for(RoleEntity role:roles) {
				RolePojo rolePojo=new RolePojo();
				rolePojo.setActive(role.getActive());
				rolePojo.setId(role.getId());
				rolePojo.setCreatedDateTime(role.getCreatedDateTime());
				rolePojo.setRole(role.getRole());
				rolePojo.setUpdatedDateTime(role.getUpdatedDateTime());
				rolePojos.add(rolePojo);
			}
		}
		catch(Exception exception) {
			System.out.println(exception.getMessage());
			throw exception;
		}
		return rolePojos;
	}

}

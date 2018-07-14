package in.vnl.spring.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.vnl.spring.entity.RoleEntity;
import in.vnl.spring.entity.pojo.role.RolePojo;
import in.vnl.spring.exceptions.validation.role.RoleNameNotUniqueException;
import in.vnl.spring.repository.RoleRepository;
import in.vnl.spring.service.RoleService;

@Component
public class RoleValidation {

	@Autowired
	private final RoleRepository roleRepository;

	public RoleValidation(RoleRepository roleRepository) {
		this.roleRepository=roleRepository;
	}

	public void create(RolePojo rolePojo) throws RoleNameNotUniqueException {
		try {
			Optional<RoleEntity> roleEntity=roleRepository.findByRole(rolePojo.getRole());
			if(roleEntity.isPresent()) {
				throw new RoleNameNotUniqueException(rolePojo.getRole());
			}
		}
		catch(RoleNameNotUniqueException exception) {
			throw exception;
		}
		
		
	}

	
	public void update(RolePojo rolePojo) {
		// TODO Auto-generated method stub
		
	}

	
	public void delete(RolePojo rolePojo) {
		// TODO Auto-generated method stub
		
	}

	
}

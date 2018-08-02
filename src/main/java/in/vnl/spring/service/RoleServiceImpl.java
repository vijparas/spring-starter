package in.vnl.spring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import in.vnl.spring.converters.role.RoleEntityToRolePojo;
import in.vnl.spring.converters.role.RolePojoToRoleEntity;
import in.vnl.spring.entity.RoleEntity;
import in.vnl.spring.entity.pojo.role.RolePojo;
import in.vnl.spring.exceptions.role.RoleNameDoesNotExistException;
import in.vnl.spring.exceptions.validation.role.RoleNameNotUniqueException;
import in.vnl.spring.repository.RoleRepository;
import in.vnl.spring.utilities.PaginationObject;
import in.vnl.spring.validation.RoleValidation;

@Service
public class RoleServiceImpl implements RoleService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final RoleValidation roleValidation;

	private final RolePojoToRoleEntity rolePojoToRoleEntityConverter;

	private final RoleEntityToRolePojo roleEntityToRolePojoConverter;
	private final static int PAGESIZE = 3;

	private final RoleRepository roleRepository;

	public RoleServiceImpl(RoleValidation roleValidation, RolePojoToRoleEntity rolePojoToRoleEntityConverter,
			RoleEntityToRolePojo roleEntityToRolePojoConverter, RoleRepository roleRepository) {

		this.roleValidation = roleValidation;
		this.rolePojoToRoleEntityConverter = rolePojoToRoleEntityConverter;
		this.roleEntityToRolePojoConverter = roleEntityToRolePojoConverter;
		this.roleRepository = roleRepository;

	}

	@Override
	public RolePojo create(RolePojo rolePojo) throws RoleNameNotUniqueException {
		try {
			roleValidation.create(rolePojo);
			RoleEntity roleEntity = rolePojoToRoleEntityConverter.convert(rolePojo);
			return roleEntityToRolePojoConverter.convert(roleRepository.save(roleEntity));
		}

		catch (RoleNameNotUniqueException exception) {
			throw exception;
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			throw exception;
		}

	}

	@Override
	public RolePojo update(RolePojo rolePojo) {
		try {
			roleValidation.update(rolePojo);
			RoleEntity roleEntity = rolePojoToRoleEntityConverter.convert(rolePojo);
			return roleEntityToRolePojoConverter.convert(roleRepository.save(roleEntity));

		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			throw exception;
		}

	}

	@Override
	public RolePojo delete(RolePojo rolePojo) {
		try {
			roleValidation.delete(rolePojo);
			RoleEntity roleEntity = rolePojoToRoleEntityConverter.convert(rolePojo);
			roleRepository.delete(roleEntity);
			return rolePojo;
		} catch (Exception exception) {
			logger.error(exception.getMessage());
			throw exception;
		}

	}

	@Override
	public Map<String, Object> displayAll(int pageNumber) {
		
		Map<String,Object> rolesMap=new HashMap<>();
		try {
			Page<RoleEntity> rolesPage=roleRepository
					.findAll(PageRequest.of(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "id"));
			
			List<RoleEntity> roles = rolesPage.getContent();
			
			List<RolePojo> rolesPojo = new ArrayList<>();
			for (RoleEntity role : roles) {
				rolesPojo.add(roleEntityToRolePojoConverter.convert(role));
			}
			int numberOfPages=rolesPage.getTotalPages();
			rolesMap.put("roles", rolesPojo);
			rolesMap.put("pagination", new PaginationObject(numberOfPages, pageNumber));
		}
		
		catch(Exception exception) {
			throw exception;
		}
		
		return rolesMap;
	}

	@Override
	public RolePojo getRole(long id) {
		try {

			Optional<RoleEntity> roleEntity = roleRepository.findById(id);
			RoleEntity role = roleEntity.get();
			RolePojo rolePojo = roleEntityToRolePojoConverter.convert(role);
			return rolePojo;

		} catch (Exception exception) {
			logger.error(exception.getMessage());
			throw exception;
		}

	}

	@Override
	public List<RolePojo> getAllRoles() {
		List<RolePojo> rolesPojo = new ArrayList<>();
		try {
			List<RoleEntity> roles = (List<RoleEntity>) roleRepository.findAll();

			for (RoleEntity role : roles) {
				rolesPojo.add(roleEntityToRolePojoConverter.convert(role));
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage());
			throw exception;
		}
		return rolesPojo;
	}

	@Override
	public List<RolePojo> saveAllRoles(List<RolePojo> roles) {
		List<RoleEntity> roleEntities = rolePojoToRoleEntityConverter.convertAllRoles(roles);
		List<RoleEntity> roleEntity = (List<RoleEntity>) roleRepository.saveAll(roleEntities);
		List<RolePojo> rolePojos = roleEntityToRolePojoConverter.convertAllRoles(roleEntity);
		return rolePojos;
	}

	@Override
	public void checkRoleName(String roleName) throws RoleNameNotUniqueException {
		try {
			Optional<RoleEntity> role = roleRepository.findByRole(roleName);
			if (role.isPresent()) {
				throw new RoleNameNotUniqueException(roleName);
			}
		} catch (RoleNameNotUniqueException exception) {
			throw exception;
		}

	}

	@Override
	public RolePojo getByRoleName(String roleName) throws RoleNameDoesNotExistException {
	try {
		Optional<RoleEntity> roleEntity=roleRepository.findByRole(roleName);
		if(roleEntity.isPresent()) {
			return roleEntityToRolePojoConverter.convert(roleEntity.get());
		}
		else {
			throw new RoleNameDoesNotExistException(roleName);
		}
	}
	catch(RoleNameDoesNotExistException exception) {
		throw exception;
	}
	catch(Exception exception) {
		throw exception;
	}
		
	}

}

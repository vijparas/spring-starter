package in.vnl.spring.service;

import java.util.List;

import in.vnl.spring.entity.pojo.role.RolePojo;
import in.vnl.spring.exceptions.role.RoleNameDoesNotExistException;
import in.vnl.spring.exceptions.validation.role.RoleNameNotUniqueException;

public interface RoleService {

	public RolePojo create(RolePojo rolePojo) throws RoleNameNotUniqueException;
	public RolePojo delete(RolePojo rolePojo);
	public RolePojo update(RolePojo rolePojo);
	public List<RolePojo> displayAll();
	public RolePojo getRole(long id);
	public List<RolePojo> getAllRoles();
	public List<RolePojo> saveAllRoles(List<RolePojo> roles);
	public void checkRoleName(String roleName) throws RoleNameNotUniqueException;
	public RolePojo getByRoleName(String roleName) throws RoleNameDoesNotExistException;
	
}

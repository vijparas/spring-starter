package in.vnl.spring.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import in.vnl.spring.converters.role.RoleEntityToRolePojo;
import in.vnl.spring.converters.role.RolePojoToRoleEntity;
import in.vnl.spring.entity.RoleEntity;
import in.vnl.spring.entity.UserEntity;
import in.vnl.spring.entity.pojo.role.RolePojo;
import in.vnl.spring.repository.RoleRepository;
import in.vnl.spring.service.RoleService;
import in.vnl.spring.service.RoleServiceImpl;
import in.vnl.spring.validation.RoleValidation;
public class RoleServiceImplTest {

	@Mock
	private RoleValidation roleValidation;
	
	@Mock
	private RolePojoToRoleEntity rolePojoToRoleEntity=new RolePojoToRoleEntity();
	
	@Mock 
	private RoleRepository roleRepository;
	
	
	private RoleService roleService;
	
	@Mock
	private RoleEntityToRolePojo roleEntityToRolePojo;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		roleService=new RoleServiceImpl(roleValidation,rolePojoToRoleEntity,roleEntityToRolePojo,roleRepository);
		//roleEntityToRolePojo=new RoleEntityToRolePojo();
		
	}
	
	@Test
	public void testFindById() {
		long id=1;
		RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(id);
        roleEntity.setActive(1);
        roleEntity.setRole("Test");
        Set<UserEntity> users=new HashSet<>();
        roleEntity.setUsers(users);
        Optional<RoleEntity> roleOptional = Optional.of(roleEntity);
        RolePojo rolePojo=new RolePojo();
        rolePojo.setId(1);
        when(roleRepository.findById(anyLong())).thenReturn(roleOptional);
        when(roleEntityToRolePojo.convert(roleEntity)).thenReturn(rolePojo);
        RolePojo roleReturned = roleService.getRole(id);

        assertNotNull("Null recipe returned", roleReturned);
        assertEquals(id, roleReturned.getId());
        verify(roleRepository, times(1)).findById(anyLong());
        verify(roleRepository, never()).findAll();
	}
	
	@Test
	public void testDelete() {
		Long idToDelete = Long.valueOf(2L);
		RolePojo role=new RolePojo();
		role.setId(idToDelete);
		role.setRole("Test role");
		RoleEntity roleEntity=new RoleEntity();
		roleEntity.setId(idToDelete);
		when(rolePojoToRoleEntity.convert(role)).thenReturn(roleEntity);
		roleService.delete(role);
		verify(roleRepository,times(1)).delete(roleEntity);
		
	}
	
	@Test
	public void testCreate() throws Exception {
		Long idToCreate=Long.valueOf(2l);
		RolePojo role=new RolePojo();
		role.setId(idToCreate);
		
		//role.setUsers(users);
		RoleEntity roleEntity=new RoleEntity();
		roleEntity.setId(idToCreate);
		roleEntity.setRole("test Role");
		
		when(rolePojoToRoleEntity.convert(role)).thenReturn(roleEntity);
		roleService.create(role);
		verify(roleRepository,times(1)).save(roleEntity);
		verify(rolePojoToRoleEntity,times(1)).convert(role);
	}
	
	@Test
	public void testUpdate() {
		Long idToUpdate=Long.valueOf(2l);
		RolePojo rolePojo=new RolePojo();
		rolePojo.setId(idToUpdate);
		
		RoleEntity roleEntity=new RoleEntity();
		roleEntity.setId(idToUpdate);
		roleEntity.setRole("updated test Role");
		when(rolePojoToRoleEntity.convert(rolePojo)).thenReturn(roleEntity);
		when(roleRepository.save(roleEntity)).thenReturn(roleEntity);
		roleService.update(rolePojo);
		verify(roleRepository,times(1)).save(roleEntity);
		
	}
	
}

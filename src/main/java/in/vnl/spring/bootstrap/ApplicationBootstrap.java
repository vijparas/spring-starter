package in.vnl.spring.bootstrap;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import in.vnl.spring.entity.pojo.role.RolePojo;
import in.vnl.spring.entity.pojo.user.UserPojo;
import in.vnl.spring.exceptions.role.RoleNameDoesNotExistException;
import in.vnl.spring.exceptions.validation.role.RoleNameNotUniqueException;
import in.vnl.spring.exceptions.validation.user.UsernameNotUniqueException;
import in.vnl.spring.service.RoleService;
import in.vnl.spring.service.UserService;
import in.vnl.spring.validation.UserValidation;

@Component
public class ApplicationBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private final UserService userService;
	private final RoleService roleService;
	private final UserValidation userValidation;
	public ApplicationBootstrap(UserService userService, RoleService roleService,UserValidation userValidation) {

		this.userService = userService;
		this.roleService = roleService;
		this.userValidation=userValidation;

	}

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			RolePojo role = this.addRoles();
			this.addUsers(role);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}

	}

	public RolePojo addRoles() {
		RolePojo rolePojo = new RolePojo();
		try {
			
			rolePojo.setActive("Active");
			rolePojo.setRole("Admin");
			rolePojo=roleService.create(rolePojo);
		}
		catch(RoleNameNotUniqueException exception) {
			try{
				rolePojo=roleService.getByRoleName(rolePojo.getRole());
			}
			catch(RoleNameDoesNotExistException exception2) {
				
			}
		}
		return rolePojo;
		
	}

	public void addUsers(RolePojo roles) {
		try {
			UserPojo userPojo = new UserPojo();
			userPojo.setUsername("admin");
			userPojo.setPassword("220386");
			userPojo.setEmail("admin@vnl.in");
			userPojo.setActive("Active");
			userPojo.setFirstName("Admin");
			userPojo.setLastName("VNL");
			userPojo.setMobile("1234567890");
			List<Long> roleId = new ArrayList<>();
			roleId.add(roles.getId());
			userPojo.setRoles(roleId);
			userService.create(userPojo);

		} catch (UsernameNotUniqueException exception) {
				
		}
		catch(Exception exception) {
			
		}
	}

}

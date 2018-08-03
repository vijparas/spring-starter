package in.vnl.spring.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import in.vnl.spring.controller.RoleController;
import in.vnl.spring.converters.role.RoleEntityToRolePojo;
import in.vnl.spring.converters.role.RolePojoToRoleEntity;
import in.vnl.spring.entity.pojo.role.RolePojo;
import in.vnl.spring.repository.RoleRepository;
import in.vnl.spring.service.RoleService;
import in.vnl.spring.validation.RoleValidation;

@RunWith(SpringRunner.class)
@SpringBootTest()
@TestPropertySource(locations = "classpath:application-test.yml")
public class RoleControllerITTest {

	@Autowired
	private RoleService roleService;

	
	@Autowired
	private RoleController roleController;

	MockMvc mockMvc;

	@Before
	public void setup() {

		mockMvc = MockMvcBuilders.standaloneSetup(roleController).build();
		
	}

	@Test
	@Transactional
	public void testCreateRole() throws Exception {

		List<RolePojo> beforeCreation=roleService.getAllRoles();
		mockMvc.perform(
				post("/role/create").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("role", "Random Role")
				.param("active","Active"))
				.andExpect(status().is3xxRedirection())

				.andExpect(view().name("redirect:/role/list"));
		
		List<RolePojo> rolePojo=roleService.getAllRoles();
		
		
		assertEquals(beforeCreation.size()+1,rolePojo.size());
	}
	
	@Test
	@Transactional
	public void testUpdateRole() throws Exception{
		RolePojo beforeUpdate=roleService.getAllRoles().get(0);
		mockMvc.perform(
				post("/role/update").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("role", "New Test Role")
				.param("id", Long.toString(beforeUpdate.getId()))
				)
				.andExpect(status().is3xxRedirection())

				.andExpect(view().name("redirect:/role/list"));
		
		RolePojo afterUpdate=roleService.getRole(beforeUpdate.getId());
		assertEquals("New Test Role", afterUpdate.getRole());
	}
	
	@Test
	@Transactional
	public void testDeleteRole() throws Exception {
		List<RolePojo> rolePojo=roleService.getAllRoles();
		mockMvc.perform(
				get("/role/delete/"+rolePojo.get(0).getId()).contentType(MediaType.APPLICATION_FORM_URLENCODED)
				
				)
				.andExpect(status().is3xxRedirection())

				.andExpect(view().name("redirect:/role/list"));
		assertEquals(rolePojo.size()-1, roleService.getAllRoles().size());
	}

}

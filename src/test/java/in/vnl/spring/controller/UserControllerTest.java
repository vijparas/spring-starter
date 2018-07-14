package in.vnl.spring.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import in.vnl.spring.controller.UserController;
import in.vnl.spring.entity.pojo.user.UserPojo;
import in.vnl.spring.service.RoleService;
import in.vnl.spring.service.UserService;

import static org.mockito.Mockito.*;
public class UserControllerTest {

	@Mock
	private UserService userService;

	@Mock
	private RoleService roleService;

	UserController userController;

	@Mock
	Model model;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		userController = new UserController(userService, roleService);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void testShowUserCreateForm() throws Exception {

		
		mockMvc.perform(get("/user/create"))
        .andExpect(status().isOk())
        .andExpect(view().name("user/create"))
        .andExpect(model().attributeExists("roles"));
	}
	
	@Test
	public void testUserCreation() throws Exception{
		
		 UserPojo userPojo=new UserPojo();
		 userPojo.setId(2l);
		 mockMvc.perform(post("/user/create")
	                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	                .param("firstName", "Paras")
	                .param("lastName", "Vij")
	                .param("email", "vijparas@gmail.com")
	                .param("password", "220386")
	        )
	                .andExpect(status().is3xxRedirection())
	                .andExpect(view().name("redirect:/user/list"));
	}
	
	@Test
	public void testShowUserUpdateForm() throws Exception {

		UserPojo userPojo=new UserPojo();
		userPojo.setId(1l);
		when(userService.getUserById(anyLong())).thenReturn(userPojo);
		mockMvc.perform(get("/user/update/1"))
        .andExpect(status().isOk())
        .andExpect(view().name("user/update"))
        .andExpect(model().attributeExists("user"));
	}
	
	@Test
	public void testUserUpdateForm() throws Exception{
		mockMvc.perform(post("/user/update")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName", "Paras")
                .param("lastName", "Vij")
                .param("email", "vijparas@gmail.com")
                .param("password", "220386")
                )
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/user/list"));
	}

}

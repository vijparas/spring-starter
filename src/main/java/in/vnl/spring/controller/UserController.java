package in.vnl.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.vnl.spring.annotations.Authorized;
import in.vnl.spring.annotations.LoginRequired;
import in.vnl.spring.annotations.ManagedMethod;
import in.vnl.spring.entity.pojo.user.UserPojo;
import in.vnl.spring.entity.pojo.user.UserUpdatePojo;
import in.vnl.spring.service.RoleService;
import in.vnl.spring.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final UserService userService;

	
	private final RoleService roleService;
	
	
	public UserController(UserService userService, RoleService roleService) {
		super();
		this.userService = userService;
		this.roleService = roleService;
	}

	@GetMapping("/create")
	@LoginRequired(roles= {"admin","superadmin","Admin"})
	@ManagedMethod(message="User Creation Module")
	public String createUserView(Model model) {

		model.addAttribute("user", new UserPojo());
		model.addAttribute("roles", roleService.getAllRoles());
		return "user/create";
	}

	@PostMapping("/create")
	public String createUser(UserPojo user, BindingResult bindingResult) {
		// System.out.println("Requst received");
		if (bindingResult.hasErrors()) {

			for (ObjectError error : bindingResult.getAllErrors()) {
				System.out.println(error.getDefaultMessage());
			}

		}
		try {
			userService.create(user);
			return "redirect:/user/list";

		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			return "redirect:/user/list";
		}

		

	}

	@GetMapping("/list")
	@LoginRequired
	public String displayUsers(Model model) {
		try {
			model.addAttribute("users", userService.displayUsers());

		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		return "user/display";
	}

	@GetMapping("/update/{id}")
	public String updateUserView(@PathVariable long id, Model model) {
		try {
			model.addAttribute("user", userService.getUserById(id));
			model.addAttribute("roles",roleService.getAllRoles());
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
		return "user/update";
	}

	@PostMapping("/update")
	public String updateUser(@ModelAttribute UserUpdatePojo userPojo, RedirectAttributes redirectAttributes) {
		try {
			userService.update(userPojo);
		} catch (Exception exception) {
			logger.error(exception.getMessage());

		}
		return "redirect:/user/list";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id,RedirectAttributes redirectAttributes) {
		try {
			UserPojo userPojo=userService.getUserById(id);
			userService.delete(userPojo);
			
		}
		catch(Exception exception) {
			logger.error(exception.getMessage());
		}
		return "redirect:/user/list";
	}
	
	@GetMapping("/password/update/{username}")
	@LoginRequired
	@Authorized
	public String updateUserPassword(@PathVariable String username) {
		
		return "user/update-password";
	}
}

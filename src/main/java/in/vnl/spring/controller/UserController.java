package in.vnl.spring.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import in.vnl.spring.entity.UserEntity;
import in.vnl.spring.entity.pojo.user.PasswordUpdatePojo;
import in.vnl.spring.entity.pojo.user.UserPojo;
import in.vnl.spring.entity.pojo.user.UserUpdatePojo;
import in.vnl.spring.exceptions.user.CurrentPasswordDoNotMatchException;
import in.vnl.spring.exceptions.user.UserNameNotFoundException;
import in.vnl.spring.exceptions.validation.user.UsernameNotUniqueException;
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

	public String createUserView(Model model) {

		model.addAttribute("user", new UserPojo());
		model.addAttribute("roles", roleService.getAllRoles());
		return "user/create-modal";
	}

	@PostMapping("/create")
	public ResponseEntity<Map<String, Object>> createUser(@ModelAttribute("user") @Valid UserPojo user,
			BindingResult bindingResult) {

		Map<String, Object> response = new HashMap<>();
		ResponseEntity<Map<String, Object>> responseEntity;
		if (bindingResult.hasErrors()) {

			Map<String, String> errors = new HashMap<>();
			for (ObjectError error : bindingResult.getAllErrors()) {

				errors.put(error.getObjectName(), error.getDefaultMessage());
			}
			response.put("errors", errors);
			responseEntity = new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			return responseEntity;
		}
		try {
			userService.create(user);
			responseEntity = new ResponseEntity<Map<String, Object>>(HttpStatus.OK);

		} catch (UsernameNotUniqueException exception) {
			response.put("error", exception.getMessage());
			responseEntity = new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		} catch (Exception exception) {

			responseEntity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;

	}

	@GetMapping(value = { "/list/{pagenumber}", "/list" })
	// @LoginRequired
	public String displayUsers(Model model, @PathVariable Optional<Integer> pagenumber) {
		int pageNumber = 1;
		try {
			if (pagenumber.isPresent()) {
				pageNumber = pagenumber.get();
			}
			model.addAttribute("users", userService.displayUsers(pageNumber));

		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
		return "user/display";
	}

	@GetMapping(value = { "/list/rest/{pagenumber}", "list/rest" })
	public ResponseEntity<Map<String, Object>> displayUsersRest(Model model,
			@PathVariable Optional<Integer> pagenumber) {
		int pageNumber = 1;
		if (pagenumber.isPresent()) {
			pageNumber = pagenumber.get();
		}
		Map<String, Object> response = new HashMap<>();
		ResponseEntity<Map<String, Object>> responseEntity;
		try {
			// Page<UserEntity> users=userService.displayUsers();

			response.put("users", userService.displayUsers(pageNumber));
			responseEntity = new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception exception) {
			responseEntity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}

	@GetMapping("/update/{id}")
	public String updateUserView(@PathVariable long id, Model model) {
		try {
			model.addAttribute("user", userService.getUserById(id));
			model.addAttribute("roles", roleService.getAllRoles());
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
		return "user/update";
	}

	@PostMapping("/update")
	public ResponseEntity<Map<String, Object>> updateUser(@ModelAttribute("user") @Valid UserUpdatePojo userPojo,
			RedirectAttributes redirectAttributes, BindingResult bindingResult) {
		Map<String, Object> response = new HashMap<>();
		ResponseEntity<Map<String, Object>> responseEntity;
		try {
			if (bindingResult.hasErrors()) {

				Map<String, String> errors = new HashMap<>();
				for (ObjectError error : bindingResult.getAllErrors()) {

					errors.put(error.getObjectName(), error.getDefaultMessage());
				}
				response.put("errors", errors);
				responseEntity = new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
				return responseEntity;
			}
			userService.update(userPojo);
			responseEntity = new ResponseEntity<Map<String, Object>>( HttpStatus.OK);
			
		} catch (Exception exception) {
			responseEntity = new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			
		}
		return responseEntity;
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		try {
			UserPojo userPojo = userService.getUserById(id);
			userService.delete(userPojo);

		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
		return "redirect:/user/list";
	}

	@GetMapping("/password/update/{username}")
	@LoginRequired
	@Authorized
	public String updateUserPasswordView(@PathVariable String username,Model model) {
		model.addAttribute("username",username);
		return "user/update-password";
	}

	public ResponseEntity<Map<String, Object>> updateUserPassword(@ModelAttribute @Valid PasswordUpdatePojo passwordUpdatePojo) {

		ResponseEntity<Map<String, Object>> responseEntity;
		try {
			userService.updateUserPassword(passwordUpdatePojo);
			responseEntity = new ResponseEntity<Map<String, Object>>(HttpStatus.OK);
		} catch (CurrentPasswordDoNotMatchException exception) {
			responseEntity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		} catch (UserNameNotFoundException exception) {
			responseEntity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		} catch (Exception exception) {
			responseEntity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
}

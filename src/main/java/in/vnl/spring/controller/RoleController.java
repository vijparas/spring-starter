package in.vnl.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.vnl.spring.entity.pojo.role.RolePojo;
import in.vnl.spring.exceptions.validation.role.RoleNameNotUniqueException;
import in.vnl.spring.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	private final RoleService roleService;
	
	public RoleController(RoleService roleService) {
		
		this.roleService = roleService;
	}

	@GetMapping("/create")
	public String createRoleView(Model model) {
		model.addAttribute("role", new RolePojo());
		return "roles/create";

	}

	@PostMapping("/create")
	public String createRole(RolePojo role,RedirectAttributes attributes) {
		try {
			roleService.create(role);
		}
		catch(RoleNameNotUniqueException exception)
		{
			attributes.addAttribute("error", exception.getMessage());
		}
		return "redirect:/role/list";

	}

	@GetMapping("/list")
	public String displayRoles(Model model) {
		model.addAttribute("roles", roleService.getAllRoles());
		return "roles/display";
	}

	@GetMapping("/update/{id}")
	public String updateRoleView(Model model, @PathVariable long id) {
		try {
			model.addAttribute("role", roleService.getRole(id));
			return "roles/update";
		} catch (Exception exception) {
			return "roles/update";
		}
	}

	@PostMapping("/update")
	public String updateRole(@ModelAttribute RolePojo rolePojo,RedirectAttributes redirectAttribute) {
		try {
			roleService.update(rolePojo);
			redirectAttribute.addFlashAttribute("message","Role with id "+rolePojo.getId()+" Has Been Updated");
			return "redirect:/role/list";
		} catch (Exception exception) {
			redirectAttribute.addFlashAttribute("message",exception.getMessage());
			return "redirect:/role/list";
		}
	}
	
	@GetMapping("/delete/{id}")
	public String deleteRole(@PathVariable long id,RedirectAttributes redirectAttribute) {
		try {
			RolePojo rolePojo=roleService.getRole(id);
			roleService.delete(rolePojo);
			redirectAttribute.addFlashAttribute("message","Role with id "+rolePojo.getId()+" Has Been Deleted");
			return "redirect:/role/list";
		}
		
		catch(Exception exception) {
			redirectAttribute.addFlashAttribute("message",exception.getMessage());
			return "redirect:/role/list";
		}
	}

}

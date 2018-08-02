package in.vnl.spring.entity.pojo.user;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import in.vnl.spring.entity.pojo.role.RolePojo;

public class UserUpdatePojo {
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String username;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	private String active;
	@Size(min = 10, max = 10)
	private String mobile;
	private List<Long> roles;
	private List<RolePojo> rolePojo = new ArrayList<>();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<Long> getRoles() {
		return roles;
	}

	public void setRoles(List<Long> roles) {
		this.roles = roles;
	}

	public List<RolePojo> getRolePojo() {
		return rolePojo;
	}

	public void setRolePojo(List<RolePojo> rolePojo) {
		this.rolePojo = rolePojo;
	}

}

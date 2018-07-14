package in.vnl.spring.entity.pojo.user;

import java.util.ArrayList;
import java.util.List;

import in.vnl.spring.entity.pojo.role.RolePojo;

public class UserUpdatePojo {
	 private String email;
	 private String username;
	 private String firstName;
	 private String lastName;
	 private int active;
	 private String mobile;
	 private List<Long> roles;
	 private List<RolePojo> rolePojo=new ArrayList<>();
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
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
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

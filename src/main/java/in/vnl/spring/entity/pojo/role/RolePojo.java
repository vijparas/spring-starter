package in.vnl.spring.entity.pojo.role;

import java.util.Set;

import in.vnl.spring.entity.pojo.BasePojo;
import in.vnl.spring.entity.pojo.user.UserPojo;

public class RolePojo extends BasePojo {
	private long id;
	private String role;
	private String active;
	private Set<UserPojo> users;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public Set<UserPojo> getUsers() {
		return users;
	}
	public void setUsers(Set<UserPojo> users) {
		this.users = users;
	}
	@Override
	public String toString() {
		return "RolePojo [id=" + id + ", role=" + role + ", active=" + active + "]";
	}
	
	
	
	
}

package in.vnl.spring.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="users")
public class UserEntity extends BaseEntity implements UserDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
 	@Email
    @Column(name = "email")
 	private String email;
 	
    @Column(name="username")
 	private String username;
 	
 	@Column(name = "password")
    private String password;
    @Transient
    private String confirmPassword;
    @Transient
    private String currentPassword;
    @NotEmpty(message="First Name Cannot Be Empty")
    @Column(name = "first_name")
    private String firstName;
    @NotEmpty(message="Last Name Cannot Be Empty")
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "active")
    private int active;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="user_role",
	joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="role_id"))
    private Set<RoleEntity> roles;
    
    @NotEmpty
    @Column(name="mobile")
    private String mobile;

    @Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdDateTime;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedDateTime;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
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

	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public void addRole(RoleEntity role) {
		if(this.getRoles()==null) {
			this.roles=new HashSet<RoleEntity>();
		}
		this.roles.add(role);
		role.addUser(this);
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public LocalDateTime getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(LocalDateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getRole()));
		});
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	public Set<String> getRolesArray() {
		Set<String> roles=new HashSet<>();
		for(RoleEntity role :this.roles) {
			roles.add(role.getRole());
		}
		return roles;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", email=" + email + ", username=" + username + ", password=" + password
				+ ", confirmPassword=" + confirmPassword + ", currentPassword=" + currentPassword + ", firstName="
				+ firstName + ", lastName=" + lastName + ", active=" + active + ", mobile=" + mobile
				+ ", updatedDateTime=" + updatedDateTime + "]";
	}
	
	
	
    
    
}

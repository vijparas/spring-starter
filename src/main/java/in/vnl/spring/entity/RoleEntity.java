package in.vnl.spring.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="role")
public class RoleEntity extends BaseEntity{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "role")
    private String role;

    @Column(name="active")
    private int active;
    
    @Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdDateTime;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedDateTime;

	@ManyToMany(mappedBy="roles")
	
	private Set<UserEntity> users;
    

    

	public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
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
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}
	
	public void addUser(UserEntity user) {
		if(this.users==null) {
			this.users=new HashSet<UserEntity>();
		}
		this.users.add(user);
		
	}

	@Override
	public String toString() {
		return "RoleEntity [id=" + id + ", role=" + role + ", active=" + active + ", createdDateTime=" + createdDateTime
				+ ", updatedDateTime=" + updatedDateTime + "]";
	}
	
	
	
	
	
}

package in.vnl.spring.service;

import java.util.List;

import in.vnl.spring.entity.pojo.user.UserPojo;
import in.vnl.spring.entity.pojo.user.UserUpdatePojo;
import in.vnl.spring.exceptions.validation.user.UsernameNotUniqueException;

public interface UserService  {
	public UserPojo create(UserPojo userPojo) throws UsernameNotUniqueException;
	public UserUpdatePojo update(UserUpdatePojo userPojo) throws UsernameNotUniqueException;
	public UserPojo delete(UserPojo userPojo);
	public List<UserPojo> displayUsers();
	public UserPojo getUserById(long id);
	
}

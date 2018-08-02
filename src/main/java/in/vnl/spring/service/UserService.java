package in.vnl.spring.service;

import java.util.List;
import java.util.Map;

import in.vnl.spring.entity.pojo.user.PasswordUpdatePojo;
import in.vnl.spring.entity.pojo.user.UserPojo;
import in.vnl.spring.entity.pojo.user.UserUpdatePojo;
import in.vnl.spring.exceptions.user.CurrentPasswordDoNotMatchException;
import in.vnl.spring.exceptions.user.UserNameNotFoundException;
import in.vnl.spring.exceptions.validation.user.UsernameNotUniqueException;

public interface UserService  {
	public UserPojo create(UserPojo userPojo) throws UsernameNotUniqueException;
	public UserUpdatePojo update(UserUpdatePojo userPojo) throws UsernameNotUniqueException;
	public UserPojo delete(UserPojo userPojo);
	public Map<String,Object> displayUsers(int pagenumber);
	public UserPojo getUserById(long id);
	public UserPojo getUserByUsername(String username) throws UserNameNotFoundException;
	public PasswordUpdatePojo updateUserPassword(PasswordUpdatePojo passwordUpdatePojo) throws CurrentPasswordDoNotMatchException,UserNameNotFoundException; 
}

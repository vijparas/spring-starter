package in.vnl.spring.entity.pojo.user;

import javax.validation.constraints.NotBlank;

public class PasswordUpdatePojo {

	@NotBlank
	private String currentPassword;
	@NotBlank
	private String newPassword;
	@NotBlank
	private String confirmPassword;
	@NotBlank
	private String username;
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	
}

package in.vnl.spring.entity.pojo;

import java.time.LocalDateTime;

public class BasePojo {

	private LocalDateTime createdDateTime;
	private LocalDateTime updatedDateTime;
	
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
	
}

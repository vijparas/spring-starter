package in.vnl.spring.validation;

import in.vnl.spring.entity.BaseEntity;
import in.vnl.spring.entity.pojo.BasePojo;

public interface BaseValidation {

	public void create(BasePojo baseEntity);
	public void update(BasePojo baseEntity);
	public void delete(BasePojo baseEntity);
}

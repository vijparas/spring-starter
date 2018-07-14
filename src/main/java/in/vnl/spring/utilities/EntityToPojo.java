package in.vnl.spring.utilities;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.vnl.spring.entity.BaseEntity;
import in.vnl.spring.entity.pojo.BasePojo;

public class EntityToPojo{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	public BasePojo convert(BaseEntity baseEntity,BasePojo basePojo) throws Exception {
		Field[] fields = basePojo.getClass().getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			try {
				PropertyDescriptor entityProperty = new PropertyDescriptor(fieldName, baseEntity.getClass());
				PropertyDescriptor pojoProperty = new PropertyDescriptor(fieldName, basePojo.getClass());
				pojoProperty.getWriteMethod().invoke(basePojo, entityProperty.getReadMethod().invoke(entityProperty));
				
			}
			catch(Exception exception) {
				logger.error(exception.getMessage());
				throw exception;
			}
		}
		return basePojo;
		
	}
}

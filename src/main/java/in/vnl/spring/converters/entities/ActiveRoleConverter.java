package in.vnl.spring.converters.entities;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ActiveRoleConverter implements  AttributeConverter<String,Integer> {

	@Override
	public Integer convertToDatabaseColumn(String active) {
		if(active.equalsIgnoreCase("Active")) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public String convertToEntityAttribute(Integer active) {
		if(active==0) {
			return "Active";
		}
		else {
			return "In Active";
		}
	}

}

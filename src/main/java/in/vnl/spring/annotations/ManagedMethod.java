package in.vnl.spring.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ManagedMethod {
	String methodIdentifier();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
   
}

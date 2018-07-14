package in.vnl.spring.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Payload;

@Target( { ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorized {
	String message() default "Access Not Given";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String[] roles() default {};
}

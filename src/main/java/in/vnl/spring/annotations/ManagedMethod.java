package in.vnl.spring.annotations;

import javax.validation.Payload;

public @interface ManagedMethod {
	String message() default "No Description Given";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
   
}

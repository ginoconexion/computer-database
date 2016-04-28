package com.formation.computerdatabase.binding.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateBefore1970Validator.class)

public @interface DateBefore1970 {
	String message() default "{DateBefore1970}";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

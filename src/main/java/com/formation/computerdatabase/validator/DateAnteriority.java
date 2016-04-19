package com.formation.computerdatabase.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateAnteriorityValidator.class)

public @interface DateAnteriority {
	String message() default "{DateAnteriority}";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

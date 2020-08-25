package com.micaelpapa.rest.login.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordFormatValidator.class)
@Documented
public @interface PasswordFormat {
	String message() default "The password must have one uppercase, two numbers and at least two lowercase";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

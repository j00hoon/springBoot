package com.springProj.pma.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


// interface 앞에 @을 붙여서 java가 이것이 annotation이라는 것을 알게 해줌
// @Target(~~) 의 의미는 이 custom validator를 field에서만 사용할 수 있도록 쓴다는 뜻, class라든지 method라든지에서는 사용 불가

// @Constraint(validatedBy = UniqueValidator.class)는 우리가 custom으로 지정할 validation rule이 들어갈 class의 이름


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
public @interface UniqueValue 
{
	String message() default "Unique Constraint violated";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}

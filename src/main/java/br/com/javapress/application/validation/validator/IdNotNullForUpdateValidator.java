package br.com.javapress.application.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.javapress.application.validation.annotation.AssertIdNotNullForUpdate;

public class IdNotNullForUpdateValidator implements ConstraintValidator<AssertIdNotNullForUpdate, Long> {
	
	public void initialize(AssertIdNotNullForUpdate constraintAnnotation) {
	}

	public boolean isValid(Long id, ConstraintValidatorContext context) {
		return id != null;
	}
}
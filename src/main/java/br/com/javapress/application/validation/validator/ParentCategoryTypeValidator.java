package br.com.javapress.application.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.javapress.application.validation.annotation.AssertParentCategoryType;
import br.com.javapress.domain.entity.post.Category;

public class ParentCategoryTypeValidator implements ConstraintValidator<AssertParentCategoryType, Category> {
	
	public void initialize(AssertParentCategoryType constraintAnnotation) {
	}

	public boolean isValid(Category category, ConstraintValidatorContext context) {
		if(category.getParent() != null){
			return category.getParent().getType().equals(category.getType());
		}
		return true;
	}
}
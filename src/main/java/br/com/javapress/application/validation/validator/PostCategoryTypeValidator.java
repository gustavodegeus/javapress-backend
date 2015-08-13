package br.com.javapress.application.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.javapress.application.validation.annotation.AssertPostCategoryType;
import br.com.javapress.domain.entity.post.CategoryType;
import br.com.javapress.domain.entity.post.Post;

public class PostCategoryTypeValidator implements ConstraintValidator<AssertPostCategoryType, Post> {
	
	private CategoryType type;
	
	
	public void initialize(AssertPostCategoryType constraintAnnotation) {
		type = constraintAnnotation.value();
	}

	
	public boolean isValid(Post post, ConstraintValidatorContext context) {
		if(post.getCategory() != null) {
			return post.getCategory().getType().equals(type);
		}
		return true;
	}
}
package br.com.javapress.application.validation.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.javapress.application.validation.validator.ParentCategoryTypeValidator;

@Target({  TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ParentCategoryTypeValidator.class)
@Documented
public @interface AssertParentCategoryType {


    String message() default "Categoria deve ser do mesmo tipo da categoria pai.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

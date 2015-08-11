package br.com.javapress.application.validation.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.javapress.application.validation.validator.IdNotNullForUpdateValidator;

@Target({  FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = IdNotNullForUpdateValidator.class)
@Documented
public @interface AssertIdNotNullForUpdate {


    String message() default "Id deve ser informado para atualizar o registro.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

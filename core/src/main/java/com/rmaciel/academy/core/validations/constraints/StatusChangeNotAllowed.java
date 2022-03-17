package com.rmaciel.academy.core.validations.constraints;

import com.rmaciel.academy.core.validations.validators.StatusChangeNotAllowedValidator;
import com.rmaciel.academy.core.validations.validators.UniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StatusChangeNotAllowedValidator.class)
@Documented
public @interface StatusChangeNotAllowed {
    String message() default "Essa alteração de status não é permitida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

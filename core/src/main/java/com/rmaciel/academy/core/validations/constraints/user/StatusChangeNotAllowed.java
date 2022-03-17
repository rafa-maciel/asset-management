package com.rmaciel.academy.core.validations.constraints.user;

import com.rmaciel.academy.core.validations.validators.UniqueValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
@Documented
public @interface StatusChangeNotAllowed {
}

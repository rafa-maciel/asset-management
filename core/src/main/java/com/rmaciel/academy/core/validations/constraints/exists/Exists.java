package com.rmaciel.academy.core.validations.constraints.exists;

import com.rmaciel.academy.core.validations.constraints.exists.services.EntityFinder;
import com.rmaciel.academy.core.validations.validators.ExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsValidator.class)
@Documented
public @interface Exists {
    String message() default "Este objeto não existe";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<? extends EntityFinder> service();
    String serviceQualifier() default "";
    String fieldName();
}

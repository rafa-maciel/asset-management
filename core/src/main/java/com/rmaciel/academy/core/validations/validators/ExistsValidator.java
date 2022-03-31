package com.rmaciel.academy.core.validations.validators;

import com.rmaciel.academy.core.validations.constraints.exists.Exists;
import com.rmaciel.academy.core.validations.constraints.exists.services.EntityFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsValidator implements ConstraintValidator<Exists, Object> {
    @Autowired
    private ApplicationContext applicationContext;

    private EntityFinder service;
    private String fieldName;

    @Override
    public void initialize(Exists exists) {
        Class<? extends EntityFinder> clazz = exists.service();
        this.fieldName = exists.fieldName();
        String serviceQualifier = exists.serviceQualifier();

        if (!serviceQualifier.equals("")) {
            this.service = this.applicationContext.getBean(serviceQualifier, clazz);
        } else {
            this.service = this.applicationContext.getBean(clazz);
        }
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return this.service.existsBy(o, this.fieldName);
    }
}

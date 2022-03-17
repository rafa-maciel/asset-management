package com.rmaciel.academy.core.validations.validators;

import com.rmaciel.academy.core.models.User;
import com.rmaciel.academy.core.models.UserStatus;
import com.rmaciel.academy.core.repositories.AssetRepository;
import com.rmaciel.academy.core.validations.constraints.user.StatusChangeNotAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StatusChangeNotAllowedValidator implements ConstraintValidator<StatusChangeNotAllowed, User> {
    @Autowired
    private AssetRepository assetRepository;

    private boolean isInactiveWithAssets(User user) {
        return user.getStatus().equals(UserStatus.INACTIVE) && assetRepository.existsByOwner(user);
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        return !isInactiveWithAssets(user);
    }
}

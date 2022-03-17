package com.rmaciel.academy.core.validations.validators;

import com.rmaciel.academy.core.models.User;
import com.rmaciel.academy.core.models.UserStatus;
import com.rmaciel.academy.core.repositories.AssetRepository;
import com.rmaciel.academy.core.repositories.UserRepository;
import com.rmaciel.academy.core.validations.constraints.StatusChangeNotAllowed;
import com.rmaciel.academy.core.validations.constraints.templates.UserTemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class StatusChangeNotAllowedValidator implements ConstraintValidator<StatusChangeNotAllowed, UserTemplateModel> {
    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private UserRepository userRepository;

    private boolean isInactiveWithAssets(UserTemplateModel userModel) {
        Optional<User> userFinder = userRepository.findById(userModel.getId());
        if (userFinder.isEmpty()) return false;

        return userModel.getStatus().equals(UserStatus.INACTIVE) && assetRepository.existsByOwner(userFinder.get());
    }

    @Override
    public boolean isValid(UserTemplateModel userModel, ConstraintValidatorContext constraintValidatorContext) {
        return !isInactiveWithAssets(userModel);
    }
}

package com.rmaciel.academy.core.validations.constraints.templates;

import com.rmaciel.academy.core.models.UserStatus;

public interface UserTemplateModel {
    Long getId();
    UserStatus getStatus();
}

package com.rmaciel.assetmanagement.user.endpoints.controllers.forms;

import com.rmaciel.academy.core.models.User;
import com.rmaciel.academy.core.models.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class UserUpdateForm {
    @NotBlank
    @Length(max = 60)
    private String name;

    @NotNull
    @Max(value = 99999)
    private Integer re;

    @NotBlank
    @Length(max = 60)
    private String department;

    @NotNull
    private UserStatus status;

    public User updateFrom(User user) {
        user.setName(name);
        user.setRe(re);
        user.setDepartment(department);
        user.setStatus(status);

        return user;
    }
}

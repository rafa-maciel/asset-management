package com.rmaciel.assetmanagement.user.endpoints.controllers.forms;

import com.rmaciel.academy.core.models.User;
import com.rmaciel.academy.core.models.UserStatus;
import com.rmaciel.academy.core.validations.constraints.StatusChangeNotAllowed;
import com.rmaciel.academy.core.validations.constraints.templates.UserTemplateModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@StatusChangeNotAllowed(message = "Não é possivel alterar status para INATIVO de usuários com ativos")
public class UserUpdateForm implements UserTemplateModel {
    @NotNull
    private Long id;

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

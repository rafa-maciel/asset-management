package com.rmaciel.assetmanagement.user.endpoints.controllers.forms;

import com.rmaciel.academy.core.models.User;
import com.rmaciel.academy.core.models.UserStatus;
import com.rmaciel.academy.core.validations.constraints.unique.Unique;
import com.rmaciel.academy.core.validations.constraints.unique.services.UserUniqueService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class UserCreateForm {
    @NotBlank
    @Length(max = 60)
    @Unique(message = "Já existe um usuário cadastrado com este nome",
        fieldName = "name", service = UserUniqueService.class)
    private String name;

    @NotNull
    @Max(value = 99999)
    @Unique(message = "Já existe um usuário cadastrado com este RE",
            fieldName = "re", service = UserUniqueService.class)
    private Integer re;

    @NotBlank
    @Length(max = 60)
    private String department;

    @NotNull
    private UserStatus status;

    public User build() {
        return new User(name, re, department, status);
    }
}

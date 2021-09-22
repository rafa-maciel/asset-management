package com.rmaciel.assetmanagement.user.endpoints.controllers.forms;

import com.rmaciel.academy.core.models.User;
import com.rmaciel.academy.core.models.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class UserForm {
    @NotEmpty @NotNull @Length(min = 3, max = 50)
    private String name;

    @NotNull
    private Integer re;

    @NotNull @NotEmpty @Length(min = 1, max = 50)
    private String department;

    @NotNull
    private UserStatus status;

    private String notes;

    public User build() {
        return new User(name, re, department, status, notes);
    }

    public User updateFrom(User user) {
        user.setName(name);
        user.setRe(re);
        user.setDepartment(department);
        user.setStatus(status);
        user.setNotes(notes);

        return user;
    }
}

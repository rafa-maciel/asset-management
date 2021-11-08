package com.rmaciel.assetmanagement.asset.endpoints.forms;

import com.rmaciel.academy.core.models.User;
import com.rmaciel.academy.core.models.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AssetOwnerForm {
    private String department;
    private Long id;
    private String name;
    private String notes;
    private Integer re;
    private UserStatus status;

    public User build() {
        return new User(id, name, re, department, status, notes);
    }
}

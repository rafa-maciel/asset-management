package com.rmaciel.assetmanagement.useraccount.endpoints.controllers.forms;

import com.rmaciel.academy.core.models.UserAccount;
import com.rmaciel.academy.core.models.UserAccountProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserAccountForm {
    @NotNull @Email @NotEmpty
    private String email;

    @NotNull @NotEmpty
    private String name;

    private String password;

    private boolean enabled = true;
    private UserAccountProfile profile = UserAccountProfile.RH;

    public UserAccount build() {
        return new UserAccount(email, name, password, enabled, profile);
    }

    public UserAccount updateFrom(UserAccount account) {
        account.setEmail(this.email);
        account.setName(this.name);

        if (password != null && !password.isEmpty()) account.setPassword(this.password);
        account.setEnabled(this.enabled);
        account.setProfile(this.profile);

        return account;
    }

}

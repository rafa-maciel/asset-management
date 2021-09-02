package com.rmaciel.assetmanagement.useraccount.endpoints.controllers.forms;

import com.rmaciel.academy.core.models.UserAccount;
import com.rmaciel.academy.core.models.UserAccountProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import static com.rmaciel.academy.core.specifications.UserAccountSpecification.*;

@Data
@AllArgsConstructor
@Slf4j
public class UserAccountSearchForm {
    private String email;
    private String name;
    private Boolean enabled;
    private UserAccountProfile profile;

    public boolean hasEmail() {
        return email != null && !email.isEmpty() && email.contains("@");
    }

    public boolean hasName() {
        return name != null && !name.isEmpty();
    }

    public boolean hasEnabledField() {
        return enabled != null;
    }

    public boolean hasProfile() {
        return profile != null;
    }

    public Specification<UserAccount> buildSpecs() {
        Specification<UserAccount> spec = likeEmail(email)
                .and(likeName(this.name))
                .and(equalProfile(this.profile))
                .and(enabledEqual(this.enabled));

        return spec;
    }

}

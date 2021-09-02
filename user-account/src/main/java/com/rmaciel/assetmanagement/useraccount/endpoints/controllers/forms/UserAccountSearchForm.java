package com.rmaciel.assetmanagement.useraccount.endpoints.controllers.forms;

import com.rmaciel.academy.core.models.UserAccount;
import com.rmaciel.academy.core.models.UserAccountProfile;
import com.rmaciel.academy.core.specifications.UserAccountSpecification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
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
        UserAccountSpecification accountSpecs = new UserAccountSpecification();
        List<Specification<UserAccount>> specs = new ArrayList<>();

        if (hasEmail()) specs.add(accountSpecs.likeEmail(this.email));
        if (hasName()) specs.add(accountSpecs.likeName(this.name));
        if (hasEnabledField()) {
            if (this.enabled) {
                specs.add(accountSpecs.isEnabled());
            } else {
                specs.add(accountSpecs.isDisabled());
            }
        }
        if (hasProfile()) specs.add(accountSpecs.equalProfile(this.profile));

        if (specs.size() < 1) return null;

        Specification<UserAccount> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            Specification.where(result).and(specs.get(i));
        }

        return result;
    }

}

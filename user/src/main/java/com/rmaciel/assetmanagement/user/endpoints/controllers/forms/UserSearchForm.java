package com.rmaciel.assetmanagement.user.endpoints.controllers.forms;

import com.rmaciel.academy.core.models.User;
import com.rmaciel.academy.core.models.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import static com.rmaciel.academy.core.specifications.UserSpecifications.*;

@Data
@AllArgsConstructor
@Slf4j
public class UserSearchForm {
    private String name;
    private Long re;
    private String department;
    private UserStatus status;

    public Specification<User> buildSpecs() {
        Specification<User> specs = likeName(name)
                .and(equalRe(re))
                .and(likeDepartment(department))
                .and(equalStatus(status));

        return specs;
    }
}

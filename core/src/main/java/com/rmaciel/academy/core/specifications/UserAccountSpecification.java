package com.rmaciel.academy.core.specifications;

import com.rmaciel.academy.core.models.UserAccount;
import com.rmaciel.academy.core.models.UserAccountProfile;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class UserAccountSpecification {
    public static Specification<UserAccount> likeEmail(String email) {
        return (Root<UserAccount> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (email == null || email.isEmpty()) return null;
            return criteriaBuilder.like(root.get("email"), "%" + email + "%");
        };
    }

    public static Specification<UserAccount> likeName(String name) {
        return (Root<UserAccount> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (name == null || name.isEmpty()) return null;
            return criteriaBuilder.like(root.get("name"), "%" + name + "%");
        };
    }

    public static Specification<UserAccount> enabledEqual(Boolean enabled) {
        return (Root<UserAccount> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (enabled == null) return null;

            return criteriaBuilder.equal(root.get("enabled"), enabled);
        };
    }

    public static Specification<UserAccount> equalProfile(UserAccountProfile profile) {
        return (Root<UserAccount> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (profile == null) return null;
            return criteriaBuilder.equal(root.get("profile"), profile);
        };
    }
}

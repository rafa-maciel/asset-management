package com.rmaciel.academy.core.specifications;

import com.rmaciel.academy.core.models.UserAccount;
import com.rmaciel.academy.core.models.UserAccountProfile;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UserAccountSpecification {
    public Specification<UserAccount> likeEmail(String email) {
        return (Root<UserAccount> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("email"), "%" + email + "%");
        };
    }

    public Specification<UserAccount> likeName(String name) {
        return (Root<UserAccount> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("name"), "%" + name + "%");
        };
    }

    public Specification<UserAccount> isEnabled() {
        return (Root<UserAccount> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("enabled"), Boolean.TRUE);
        };
    }

    public Specification<UserAccount> isDisabled() {
        return (Root<UserAccount> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("enabled"), Boolean.FALSE);
        };
    }

    public Specification<UserAccount> equalProfile(UserAccountProfile profile) {
        return (Root<UserAccount> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("profile"), profile);
        };
    }
}

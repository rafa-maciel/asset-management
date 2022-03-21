package com.rmaciel.academy.core.specifications;

import com.rmaciel.academy.core.models.User;
import com.rmaciel.academy.core.models.UserStatus;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class UserSpecifications {
    public static Specification<User> likeName(String name) {
        return (Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (name == null || name.isEmpty()) return null;
            return criteriaBuilder.like(root.get("name"), "%" + name + "%");
        };
    }

    public static Specification<User> equalRe(Long re) {
        return (Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (re == null || re < 1) return null;
            return criteriaBuilder.equal(root.get("re"), re);
        };
    }

    public static Specification<User> likeDepartment(String department) {
        return (Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (department == null || department.isEmpty()) return null;
            return criteriaBuilder.like(root.get("department"), "%" + department + "%");
        };
    }

    public static Specification<User> equalStatus(UserStatus status) {
        return (Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (status == null) return null;
            return criteriaBuilder.equal(root.get("status"), status);
        };
    }

    public static Specification<User> notEqualStatus(UserStatus status){
        return (Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (status == null) return null;
            return criteriaBuilder.notEqual(root.get("status"), status);
        };
    }
}

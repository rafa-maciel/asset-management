package com.rmaciel.academy.core.specifications;

import com.rmaciel.academy.core.models.Model;
import com.rmaciel.academy.core.models.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class ModelSpecifications {

    public static Specification<Model> likeTitle(String title) {
        return (Root<Model> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (title == null || title.isEmpty()) return null;
            return criteriaBuilder.like(root.get("title"), "%" + title + "%");
        };
    }

    public static Specification<Model> likeBrand(String brand) {
        return (Root<Model> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (brand == null || brand.isEmpty()) return null;
            return criteriaBuilder.like(root.get("brand"), "%" + brand + "%");
        };
    }

    public static Specification<Model> likeType(String type) {
        return (Root<Model> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (type == null || type.isEmpty()) return null;
            return criteriaBuilder.like(root.get("type"), "%" + type + "%");
        };
    }
}

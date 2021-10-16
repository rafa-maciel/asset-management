package com.rmaciel.academy.core.specifications;

import com.rmaciel.academy.core.models.Location;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class LocationSpecifications {

    public static Specification<Location> likeTitle(String title) {
        return (Root<Location> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (title == null || title.isEmpty()) return null;
            return criteriaBuilder.like(root.get("title"), "%" + title + "%");
        };
    }
}

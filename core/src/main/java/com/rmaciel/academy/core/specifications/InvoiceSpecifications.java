package com.rmaciel.academy.core.specifications;

import com.rmaciel.academy.core.models.Invoice;
import com.rmaciel.academy.core.specifications.utils.DateSearchType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

public abstract class InvoiceSpecifications {
    public static Specification<Invoice> equalNumber(Long number) {
        return (Root<Invoice> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (number == null || number < 1) return null;

            return criteriaBuilder.equal(root.get("number"), number);
        };
    }

    public static Specification<Invoice> likeVendor(String vendor) {
        return (Root<Invoice> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (vendor == null || vendor.isEmpty()) return null;

            return criteriaBuilder.like(root.get("vendor"), "%" + vendor + "%");
        };
    }

    public static Specification<Invoice> likeVendorCNPJ(String vendorCNPJ) {
        return (Root<Invoice> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (vendorCNPJ == null || vendorCNPJ.isEmpty()) return null;

            return criteriaBuilder.like(root.get("vendorCNPJ"), "%" + vendorCNPJ + "%");
        };
    }

    public static Specification<Invoice> searchStartsAtDate(LocalDate startsAt, LocalDate startsAtMax, DateSearchType type) {
        return (Root<Invoice> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (startsAt == null) return null;
            if (type == DateSearchType.BETWEEN && startsAtMax == null) return null;

            switch (type) {
                case EQUAL:
                     return criteriaBuilder.equal(root.get("startsAt"), startsAt);

                case LESS_THAN:
                    return criteriaBuilder.lessThanOrEqualTo(root.get("startsAt"), startsAt);

                case GREATER_THAN:
                    return criteriaBuilder.greaterThanOrEqualTo(root.get("startsAt"), startsAt);

                case BETWEEN:
                    return criteriaBuilder.between(root.get("startsAt"), startsAt, startsAtMax);

                default:
                    return null;
            }
        };
    }

    public static Specification<Invoice> searchEndsAtDate(LocalDate endsAt, LocalDate endsAtMax, DateSearchType type) {
        return (Root<Invoice> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (endsAt == null) return null;
            if (type == DateSearchType.BETWEEN && endsAtMax == null) return null;

            switch (type) {
                case EQUAL:
                    return criteriaBuilder.equal(root.get("endsAt"), endsAt);

                case LESS_THAN:
                    return criteriaBuilder.lessThanOrEqualTo(root.get("endsAt"), endsAt);

                case GREATER_THAN:
                    return criteriaBuilder.greaterThanOrEqualTo(root.get("endsAt"), endsAt);

                case BETWEEN:
                    return criteriaBuilder.between(root.get("endsAt"), endsAt, endsAtMax);

                default:
                    return null;
            }
        };
    }

}

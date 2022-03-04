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

    public static Specification<Invoice> searchDate(LocalDate date, LocalDate dateMax, DateSearchType type) {
        return (Root<Invoice> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (date == null) return null;
            if (type == DateSearchType.BETWEEN && dateMax == null) return null;

            switch (type) {
                case EQUAL:
                     return criteriaBuilder.equal(root.get("date"), date);

                case LESS_THAN:
                    return criteriaBuilder.lessThanOrEqualTo(root.get("date"), date);

                case GREATER_THAN:
                    return criteriaBuilder.greaterThanOrEqualTo(root.get("date"), date);

                case BETWEEN:
                    return criteriaBuilder.between(root.get("date"), date, dateMax);

                default:
                    return null;
            }
        };
    }
}

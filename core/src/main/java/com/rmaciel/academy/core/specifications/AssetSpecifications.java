package com.rmaciel.academy.core.specifications;

import com.rmaciel.academy.core.models.Asset;
import com.rmaciel.academy.core.models.AssetStatus;
import com.rmaciel.academy.core.specifications.utils.DateSearchType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

public abstract class AssetSpecifications {

    public static Specification<Asset> equalOwner(Long ownerId) {
        return (Root<Asset> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (ownerId == null || ownerId < 1) return null;

            return criteriaBuilder.equal(root.get("owner"), ownerId);
        };
    }

    public static Specification<Asset> likeOwnerName(String ownerName) {
        return (Root<Asset> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (ownerName == null || ownerName.isEmpty()) return null;

            return criteriaBuilder.like(root.get("owner").get("name"), "%" + ownerName + "%");
        };
    }

    public static Specification<Asset> equalOwnerRE(Integer ownerRe) {
        return (Root<Asset> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (ownerRe == null || ownerRe < 1) return null;

            return criteriaBuilder.equal(root.get("owner").get("re"), ownerRe);
        };
    }

    public static Specification<Asset> equalLocation(Long locationId) {
        return (Root<Asset> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (locationId == null || locationId < 1) return null;

            return criteriaBuilder.equal(root.get("location"), locationId);
        };
    }

    public static Specification<Asset> likeLocationTitle(String locationTitle) {
        return (Root<Asset> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (locationTitle == null || locationTitle.isEmpty()) return null;

            return criteriaBuilder.like(root.get("location").get("title"), "%" + locationTitle + "%");
        };
    }

    public static Specification<Asset> equalModel(Long modelId) {
        return (Root<Asset> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (modelId == null || modelId < 1) return null;

            return criteriaBuilder.equal(root.get("model"), modelId);
        };
    }

    public static Specification<Asset> likeModelTitle(String modelTitle) {
        return (Root<Asset> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (modelTitle == null || modelTitle.isEmpty()) return null;

            return criteriaBuilder.like(root.get("model").get("title"), "%" + modelTitle + "%");
        };
    }

    public static Specification<Asset> likeModelType(String modelType) {
        return (Root<Asset> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (modelType == null || modelType.isEmpty()) return null;

            return criteriaBuilder.like(root.get("model").get("type"), "%" + modelType + "%");
        };
    }

    public static Specification<Asset> equalContractNumber(Long contractNumber) {
        return (Root<Asset> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (contractNumber == null || contractNumber < 1) return null;

            return criteriaBuilder.equal(root.get("contract").get("number"), contractNumber);
        };
    }

    public static Specification<Asset> likeContractVendor(String contractVendor) {
        return (Root<Asset> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (contractVendor == null || contractVendor.isEmpty()) return null;

            return criteriaBuilder.like(root.get("contract").get("vendor"), "%" + contractVendor + "%");
        };
    }

    public static Specification<Asset> equalContractVendorCNPJ(String contractVendorCNPJ) {
        return (Root<Asset> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (contractVendorCNPJ == null || contractVendorCNPJ.isEmpty()) return null;

            return criteriaBuilder.equal(root.get("contract").get("vendorCNPJ"), contractVendorCNPJ);
        };
    }

    public static Specification<Asset> equalCompanyIdentification(Integer companyIdentification) {
        return (Root<Asset> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (companyIdentification == null || companyIdentification < 1) return null;

            return criteriaBuilder.equal(root.get("companyIdentification"), companyIdentification);
        };
    }

    public static Specification<Asset> equalStatus(AssetStatus status) {
        return (Root<Asset> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (status == null) return null;

            return criteriaBuilder.equal(root.get("status"), status);
        };
    }

    public static Specification<Asset> equalChipIdentification(String chipIdentification) {
        return (Root<Asset> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (chipIdentification == null || chipIdentification.isEmpty()) return null;

            return criteriaBuilder.equal(root.get("chipIdentification"), chipIdentification);
        };
    }

    public static Specification<Asset> equalLineIdentification(String lineIdentification) {
        return (Root<Asset> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (lineIdentification == null || lineIdentification.isEmpty()) return null;

            return criteriaBuilder.equal(root.get("lineIdentification"), lineIdentification);
        };
    }

    public static Specification<Asset> equalImei(String imei) {
        return (Root<Asset> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (imei == null || imei.isEmpty()) return null;

            return criteriaBuilder.equal(root.get("imei"), imei);
        };
    }



    public static Specification<Asset> equalTag(String tag) {
        return (Root<Asset> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (tag == null || tag.isEmpty()) return null;

            return criteriaBuilder.equal(root.get("tag"), tag);
        };
    }

    public static Specification<Asset> likeSerialNumber(String serialNumber) {
        return (Root<Asset> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (serialNumber == null || serialNumber.isEmpty()) return null;

            return criteriaBuilder.like(root.get("serialNumber"), "%" + serialNumber + "%");
        };
    }

    public static Specification<Asset> likeHostname(String hostname) {
        return (Root<Asset> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (hostname == null || hostname.isEmpty()) return null;

            return criteriaBuilder.like(root.get("hostname"), "%" + hostname + "%");
        };
    }

    public static Specification<Asset> searchDateEndOfWarranty(LocalDate endOfWarranty, LocalDate endOfWarrantyMax, DateSearchType type) {
        return (Root<Asset> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (endOfWarranty == null) return null;
            if (type == DateSearchType.BETWEEN && endOfWarrantyMax == null) return null;

            switch (type) {
                case EQUAL:
                    return criteriaBuilder.equal(root.get("endOfWarranty"), endOfWarranty);

                case LESS_THAN:
                    return criteriaBuilder.lessThanOrEqualTo(root.get("endOfWarranty"), endOfWarranty);

                case GREATER_THAN:
                    return criteriaBuilder.greaterThanOrEqualTo(root.get("endOfWarranty"), endOfWarranty);

                case BETWEEN:
                    return criteriaBuilder.between(root.get("endOfWarranty"), endOfWarranty, endOfWarrantyMax);

                default:
                    return null;
            }
        };
    }
}

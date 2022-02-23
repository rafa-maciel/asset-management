package com.rmaciel.academy.core.specifications;

import com.rmaciel.academy.core.models.Asset;
import com.rmaciel.academy.core.models.AssetStatus;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

    public static Specification<Asset> likeCompanyIdentification(String companyIdentification) {
        return (Root<Asset> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            if (companyIdentification == null || companyIdentification.isEmpty()) return null;

            return criteriaBuilder.like(root.get("companyIdentification"), "%" + companyIdentification + "%");
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
}

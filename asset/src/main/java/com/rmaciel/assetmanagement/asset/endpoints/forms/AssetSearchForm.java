package com.rmaciel.assetmanagement.asset.endpoints.forms;

import com.rmaciel.academy.core.models.Asset;
import com.rmaciel.academy.core.models.AssetStatus;
import com.rmaciel.academy.core.specifications.AssetSpecifications;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import static com.rmaciel.academy.core.specifications.AssetSpecifications.*;

@Data
@AllArgsConstructor
public class AssetSearchForm {
    private Long ownerId;
    private String ownerName;
    private Integer ownerRe;
    private Long locationId;
    private String locationTitle;
    private Long modelId;
    private String modelTitle;
    private Long contractNumber;
    private String contractVendor;
    private String contractVendorCNPJ;
    private String companyIdentification;
    private AssetStatus status;
    private String chipIdentification;
    private String lineIdentification;

    public Specification<Asset> buildSpecs() {
        Specification<Asset> specs = equalOwner(ownerId)
                .and(likeOwnerName(ownerName))
                .and(equalOwnerRE(ownerRe))
                .and(equalLocation(locationId))
                .and(likeLocationTitle(locationTitle))
                .and(equalModel(modelId))
                .and(likeModelTitle(modelTitle))
                .and(equalContractNumber(contractNumber))
                .and(likeContractVendor(contractVendor))
                .and(equalContractVendorCNPJ(contractVendorCNPJ))
                .and(likeCompanyIdentification(companyIdentification))
                .and(equalStatus(status))
                .and(equalChipIdentification(chipIdentification))
                .and(equalLineIdentification(lineIdentification));

        return specs;
    }
}

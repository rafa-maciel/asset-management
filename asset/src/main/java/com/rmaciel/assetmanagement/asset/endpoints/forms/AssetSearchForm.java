package com.rmaciel.assetmanagement.asset.endpoints.forms;

import com.rmaciel.academy.core.models.Asset;
import com.rmaciel.academy.core.models.AssetStatus;
import com.rmaciel.academy.core.specifications.utils.DateSearchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

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
    private String modelType;
    private Long contractNumber;
    private String contractVendor;
    private String contractVendorCNPJ;
    private Integer companyIdentification;
    private AssetStatus status;
    private String chipIdentification;
    private String lineIdentification;
    private LocalDate endOfWarranty;
    private LocalDate endOfWarrantyMax;
    private DateSearchType modeSearchEndOfWarranty;
    private String imei;
    private String tag;
    private String hostname;
    private String serialNumber;

    public Specification<Asset> buildSpecs() {
        return equalOwner(ownerId)
                .and(likeOwnerName(ownerName))
                .and(equalOwnerRE(ownerRe))
                .and(equalLocation(locationId))
                .and(likeLocationTitle(locationTitle))
                .and(equalModel(modelId))
                .and(likeModelTitle(modelTitle))
                .and(likeModelType(modelType))
                .and(equalContractNumber(contractNumber))
                .and(likeContractVendor(contractVendor))
                .and(equalContractVendorCNPJ(contractVendorCNPJ))
                .and(equalCompanyIdentification(companyIdentification))
                .and(equalStatus(status))
                .and(equalChipIdentification(chipIdentification))
                .and(equalLineIdentification(lineIdentification))
                .and(equalImei(imei))
                .and(equalTag(tag))
                .and(likeSerialNumber(serialNumber))
                .and(likeHostname(hostname))
                .and(searchDateEndOfWarranty(endOfWarranty, endOfWarrantyMax, modeSearchEndOfWarranty));
    }
}

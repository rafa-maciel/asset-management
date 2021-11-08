package com.rmaciel.assetmanagement.asset.endpoints.forms;

import com.rmaciel.academy.core.models.Asset;
import com.rmaciel.academy.core.models.AssetStatus;
import com.rmaciel.academy.core.models.Contract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class AssetImportForm {
    @Length(max = 60)
    private String companyIdentification;

    @NotNull
    private AssetStatus status;

    @Length(max = 60)
    private String chipIdentification;

    @Length(max = 60)
    private String lineIdentification;

    private AssetOwnerForm owner;
    private AssetModelForm model;
    private AssetLocationForm location;
    private AssetContractForm contract;

    public Asset build() {
        Asset asset = new Asset(owner.build(), location.build(), model.build(), companyIdentification, status);
        asset.setChipIdentification(chipIdentification);
        asset.setLineIdentification(lineIdentification);

        Contract bContract = contract.build(asset);
        asset.setContract(bContract);

        return asset;
    }


}

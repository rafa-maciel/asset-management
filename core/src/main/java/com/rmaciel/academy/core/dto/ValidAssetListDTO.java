package com.rmaciel.academy.core.dto;

import com.rmaciel.academy.core.models.Asset;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidAssetListDTO {
    private Boolean hasValidationErrors;
    private List<Asset> validAssets;
    private List<InvalidAssetDataDTO> invalidAssets;

    public ValidAssetListDTO() {
        this.hasValidationErrors = false;
        this.validAssets = new ArrayList<>();
        this.invalidAssets = new ArrayList<>();
    }

    public void addInvalidAssetData(InvalidAssetDataDTO assetData) {
        this.invalidAssets.add(assetData);
    }

    public void addValidAsset(Asset asset) {
        this.validAssets.add(asset);
    }



}

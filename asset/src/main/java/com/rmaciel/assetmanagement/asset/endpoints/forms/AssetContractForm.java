package com.rmaciel.assetmanagement.asset.endpoints.forms;

import com.rmaciel.academy.core.models.Asset;
import com.rmaciel.academy.core.models.Contract;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class AssetContractForm {
    private Long number;
    private String vendor;
    private String vendorCNPJ;
    private LocalDate startsAt;
    private LocalDate endsAt;

    public Contract build(Asset asset) {
        return new Contract(asset, number, vendor, vendorCNPJ, startsAt, endsAt);
    }
}

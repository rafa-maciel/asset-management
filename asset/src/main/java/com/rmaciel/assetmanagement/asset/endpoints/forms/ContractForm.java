package com.rmaciel.assetmanagement.asset.endpoints.forms;

import com.rmaciel.academy.core.models.Asset;
import com.rmaciel.academy.core.models.Contract;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ContractForm {
    private Long number;
    private String vendor;
    private String vendorCNPJ;
    private LocalDate startsAt;
    private LocalDate endsAt;

    public Contract build(Asset asset) {
        return new Contract(number, vendor, vendorCNPJ, startsAt, endsAt);
    }

    public Contract updateFrom(Contract contract) {
        contract.setNumber(number);
        contract.setVendor(vendor);
        contract.setVendorCNPJ(vendorCNPJ);
        contract.setStartsAt(startsAt);
        contract.setEndsAt(endsAt);

        return contract;
    }
}

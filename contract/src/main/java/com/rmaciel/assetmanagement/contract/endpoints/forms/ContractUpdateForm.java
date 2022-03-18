package com.rmaciel.assetmanagement.contract.endpoints.forms;

import com.rmaciel.academy.core.models.Contract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ContractUpdateForm {
    @Length(max = 30)
    @NotBlank
    private String number;

    @Length(max = 50)
    @NotBlank
    private String vendor;

    @CNPJ
    @NotBlank
    private String vendorCNPJ;

    @NotNull
    private LocalDate startsAt;

    @NotNull
    private LocalDate endsAt;

    public Contract updateFrom(Contract contract) {
        contract.setNumber(number);
        contract.setVendor(vendor);
        contract.setVendorCNPJ(vendorCNPJ);
        contract.setStartsAt(startsAt);
        contract.setEndsAt(endsAt);

        return contract;
    }
}

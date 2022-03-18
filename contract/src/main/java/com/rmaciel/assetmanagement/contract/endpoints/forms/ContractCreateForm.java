package com.rmaciel.assetmanagement.contract.endpoints.forms;

import com.rmaciel.academy.core.models.Contract;
import com.rmaciel.academy.core.validations.constraints.unique.Unique;
import com.rmaciel.academy.core.validations.constraints.unique.services.ContractUniqueService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ContractCreateForm {
    @Length(max = 30)
    @NotBlank
    @Unique(message = "Já existe um contrato com este número",
            service = ContractUniqueService.class,
            fieldName = "number")
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

    public Contract build() {
        return new Contract(number, vendor, vendorCNPJ, startsAt, endsAt);
    }
}

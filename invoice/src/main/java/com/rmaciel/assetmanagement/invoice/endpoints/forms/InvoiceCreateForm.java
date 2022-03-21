package com.rmaciel.assetmanagement.invoice.endpoints.forms;

import com.rmaciel.academy.core.models.Invoice;
import com.rmaciel.academy.core.validations.constraints.unique.Unique;
import com.rmaciel.academy.core.validations.constraints.unique.services.InvoiceUniqueService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class InvoiceCreateForm {
    @Max(999999999)
    @Min(1)
    @NotNull
    @Unique(message = "Já existe uma nota fiscal com este número",
            service = InvoiceUniqueService.class,
            fieldName = "number")
    private Integer number;

    @NotBlank
    @Length(max = 50)
    private String vendor;

    @NotBlank
    @CNPJ(message = "Este CNPJ não é valido")
    private String vendorCNPJ;

    @NotNull
    private LocalDate date;

    public Invoice build() {
        return new Invoice(number, vendor, vendorCNPJ, date);
    }
}

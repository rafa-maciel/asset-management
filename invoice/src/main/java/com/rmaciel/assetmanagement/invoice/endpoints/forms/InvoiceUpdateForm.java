package com.rmaciel.assetmanagement.invoice.endpoints.forms;

import com.rmaciel.academy.core.models.Invoice;
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
public class InvoiceUpdateForm {
    @Max(999999999)
    @Min(1)
    @NotNull
    private Integer number;

    @NotBlank
    @Length(max = 50)
    private String vendor;

    @NotBlank
    @CNPJ
    private String vendorCNPJ;

    @NotNull
    private LocalDate date;

    public Invoice updateFrom(Invoice invoice) {
        invoice.setNumber(number);
        invoice.setVendor(vendor);
        invoice.setVendorCNPJ(vendorCNPJ);
        invoice.setDate(date);

        return invoice;
    }
}

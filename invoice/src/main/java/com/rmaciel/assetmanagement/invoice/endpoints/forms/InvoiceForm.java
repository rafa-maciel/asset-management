package com.rmaciel.assetmanagement.invoice.endpoints.forms;

import com.rmaciel.academy.core.models.Invoice;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class InvoiceForm {
    private Long number;
    private String vendor;
    private String vendorCNPJ;
    private LocalDate startsAt;
    private LocalDate endsAt;

    public Invoice build() {
        return new Invoice(number, vendor, vendorCNPJ, startsAt, endsAt);
    }

    public Invoice updateFrom(Invoice invoice) {
        invoice.setNumber(number);
        invoice.setVendor(vendor);
        invoice.setVendorCNPJ(vendorCNPJ);
        invoice.setStartsAt(startsAt);
        invoice.setEndsAt(endsAt);

        return invoice;
    }
}
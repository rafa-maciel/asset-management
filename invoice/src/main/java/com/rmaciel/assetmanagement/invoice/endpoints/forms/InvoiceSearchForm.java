package com.rmaciel.assetmanagement.invoice.endpoints.forms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rmaciel.academy.core.models.Invoice;
import com.rmaciel.academy.core.specifications.utils.DateSearchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static com.rmaciel.academy.core.specifications.InvoiceSpecifications.*;

@Data
@AllArgsConstructor
public class InvoiceSearchForm {
    private Long number;
    private String vendor;
    private String vendorCNPJ;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateMax;

    private DateSearchType dateSearchType = DateSearchType.EQUAL;

    public Specification<Invoice> buildSpecs() {
        Specification<Invoice> specs = equalNumber(number)
                .and(likeVendor(vendor))
                .and(likeVendorCNPJ(vendorCNPJ))
                .and(searchDate(date, dateMax, dateSearchType));

        return specs;
    }


}

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
    private LocalDate startsAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startAtMax;

    private DateSearchType startsAtSearchType = DateSearchType.EQUAL;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endsAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endsAtMax;

    private DateSearchType endsAtSearchType = DateSearchType.EQUAL;

    public Specification<Invoice> buildSpecs() {
        Specification<Invoice> specs = equalNumber(number)
                .and(likeVendor(vendor))
                .and(likeVendorCNPJ(vendorCNPJ))
                .and(searchStartsAtDate(startsAt, startAtMax, startsAtSearchType))
                .and(searchEndsAtDate(endsAt, endsAtMax, endsAtSearchType));

        return specs;
    }


}

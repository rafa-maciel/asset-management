package com.rmaciel.assetmanagement.invoice.endpoints.forms;

import com.rmaciel.academy.core.models.Invoice;
import com.rmaciel.academy.core.specifications.utils.DateSearchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

import static com.rmaciel.academy.core.specifications.InvoiceSpecifications.*;

@Data
@AllArgsConstructor
public class InvoiceSearchForm {
    private Long number;
    private String vendor;
    private String vendorCNPJ;
    private LocalDate startsAt;
    private LocalDate startAtMax;
    private DateSearchType startsAtSearchType = DateSearchType.EQUAL;
    private LocalDate endsAt;
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

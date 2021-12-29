package com.rmaciel.assetmanagement.contract.endpoints.forms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rmaciel.academy.core.models.Contract;
import com.rmaciel.academy.core.specifications.utils.DateSearchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static com.rmaciel.academy.core.specifications.ContractSpecifications.*;

@Data
@AllArgsConstructor
public class ContractSearchForm {
    private Long number;
    private String vendor;
    private String vendorCNPJ;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startsAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startsAtMax;
    private DateSearchType startsAtSearchType = DateSearchType.EQUAL;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endsAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endsAtMax;
    private DateSearchType endsAtSearchType = DateSearchType.EQUAL;

    public Specification<Contract> buildSpecs() {
        Specification<Contract> specs = equalNumber(number)
                .and(likeVendor(vendor))
                .and(likeVendorCNPJ(vendorCNPJ))
                .and(searchStartsAtDate(startsAt, startsAtMax, startsAtSearchType))
                .and(searchEndsAtDate(endsAt, endsAtMax, endsAtSearchType));

        return specs;
    }


}

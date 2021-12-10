package com.rmaciel.assetmanagement.contract.endpoints.forms;

import com.rmaciel.academy.core.models.Contract;
import com.rmaciel.academy.core.specifications.ContractSpecifications;
import com.rmaciel.academy.core.specifications.utils.DateSearchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

import static com.rmaciel.academy.core.specifications.ContractSpecifications.*;

@Data
@AllArgsConstructor
public class ContractSearchForm {
    private Long number;
    private String vendor;
    private String vendorCNPJ;
    private LocalDate startsAt;
    private LocalDate startAtMax;
    private DateSearchType startsAtSearchType = DateSearchType.EQUAL;
    private LocalDate endsAt;
    private LocalDate endsAtMax;
    private DateSearchType endsAtSearchType = DateSearchType.EQUAL;

    public Specification<Contract> buildSpecs() {
        Specification<Contract> specs = equalNumber(number)
                .and(likeVendor(vendor))
                .and(likeVendorCNPJ(vendorCNPJ))
                .and(searchStartsAtDate(startsAt, startAtMax, startsAtSearchType))
                .and(searchEndsAtDate(endsAt, endsAtMax, endsAtSearchType));

        return specs;
    }


}

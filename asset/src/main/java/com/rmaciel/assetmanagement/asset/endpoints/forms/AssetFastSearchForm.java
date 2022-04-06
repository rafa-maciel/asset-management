package com.rmaciel.assetmanagement.asset.endpoints.forms;

import com.rmaciel.academy.core.models.Asset;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import static com.rmaciel.academy.core.specifications.AssetSpecifications.*;

@Data
@AllArgsConstructor
public class AssetFastSearchForm {
    private String parameter;

    public Specification<Asset> buildSpecs() {
        return likeHostname(parameter)
                .or(likeOwnerName(parameter))
                .or(likeSerialNumber(parameter))
                .or(equalTag(parameter));
    }
}

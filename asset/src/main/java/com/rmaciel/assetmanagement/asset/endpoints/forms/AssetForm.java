package com.rmaciel.assetmanagement.asset.endpoints.forms;

import com.rmaciel.academy.core.models.*;
import com.rmaciel.academy.core.repositories.LocationRepository;
import com.rmaciel.academy.core.repositories.ModelRepository;
import com.rmaciel.academy.core.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class AssetForm {
    @NotNull
    private Long ownerId;
    @NotNull
    private Long locationId;
    @NotNull
    private Long modelId;

    @Length(max = 60)
    private String companyIdentification;

    @NotNull
    private AssetStatus status;

    @Length(max = 60)
    private String chipIdentification;

    @Length(max = 60)
    private String lineIdentification;

    public Asset build(UserRepository userRepository,
                       LocationRepository locationRepository,
                       ModelRepository modelRepository) {

        User owner = userRepository.findById(ownerId).get();
        Location location = locationRepository.findById(locationId).get();
        Model model = modelRepository.findById(modelId).get();

        Asset asset = new Asset(owner, location, model, companyIdentification, status);
        asset.setChipIdentification(this.chipIdentification);
        asset.setLineIdentification(this.lineIdentification);

        return asset;
    }

    public Asset updateFrom(Asset asset,
                            UserRepository userRepository,
                            LocationRepository locationRepository,
                            ModelRepository modelRepository) {

        User owner = userRepository.findById(ownerId).get();
        Location location = locationRepository.findById(locationId).get();
        Model model = modelRepository.findById(modelId).get();

        asset.setOwner(owner);
        asset.setLocation(location);
        asset.setModel(model);
        asset.setStatus(this.status);
        asset.setCompanyIdentification(this.companyIdentification);
        asset.setChipIdentification(this.chipIdentification);
        asset.setLineIdentification(this.lineIdentification);

        return asset;
    }
}

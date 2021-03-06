package com.rmaciel.assetmanagement.location.endpoints.forms;

import com.rmaciel.academy.core.models.Location;
import com.rmaciel.academy.core.validations.constraints.unique.Unique;
import com.rmaciel.academy.core.validations.constraints.unique.services.LocationUniqueService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class LocationCreateForm {
    @NotBlank
    @Length(min = 2, max = 60)
    @Unique(message = "Já existe uma locação com o mesmo titulo",
            fieldName = "title",
            service = LocationUniqueService.class)
    private String title;

    @Length(max = 60)
    private String address;

    public Location build() {
        return new Location(title, address);
    }
}

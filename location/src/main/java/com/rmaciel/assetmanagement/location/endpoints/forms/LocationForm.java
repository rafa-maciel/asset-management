package com.rmaciel.assetmanagement.location.endpoints.forms;

import com.rmaciel.academy.core.models.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class LocationForm {
    @NotEmpty @NotNull @Length(min = 2, max = 60)
    private String title;

    @Length(max = 60)
    private String address;

    public Location build() {
        return new Location(title, address);
    }

    public Location updateFrom(Location location) {
        location.setTitle(title);
        location.setAddress(address);

        return location;
    }
}

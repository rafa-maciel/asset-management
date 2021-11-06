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
    @NotEmpty @NotNull @Length(min = 2, max = 30)
    private String title;

    @Length(max = 120)
    private String notes;

    public Location build() {
        return new Location(title, notes);
    }

    public Location updateFrom(Location location) {
        location.setTitle(title);
        location.setNotes(notes);

        return location;
    }
}

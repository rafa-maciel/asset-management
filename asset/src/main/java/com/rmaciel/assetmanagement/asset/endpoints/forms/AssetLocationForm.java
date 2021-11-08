package com.rmaciel.assetmanagement.asset.endpoints.forms;

import com.rmaciel.academy.core.models.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AssetLocationForm {
    private Long id;
    private String title;
    private String notes;

    public Location build() {
        return new Location(id, title, notes);
    }
}

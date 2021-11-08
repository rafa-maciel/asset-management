package com.rmaciel.assetmanagement.asset.endpoints.forms;

import com.rmaciel.academy.core.models.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AssetModelForm {
    private Long id;
    private String brand;
    private String title;
    private String type;

    public Model build() {
        return new Model(id, title, brand, type);
    }
}

package com.rmaciel.assetmanagement.model.endpoints.forms;

import com.rmaciel.academy.core.models.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class ModelUpdateForm {

    @NotBlank
    @Length(max = 30)
    private String title;

    @NotBlank
    @Length(max = 30)
    private String brand;

    @NotBlank
    @Length(max = 50)
    private String type;

    public Model updateFrom(Model model) {
        model.setTitle(title);
        model.setBrand(brand);
        model.setType(type);

        return model;
    }
}

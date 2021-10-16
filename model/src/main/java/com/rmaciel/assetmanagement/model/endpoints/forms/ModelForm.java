package com.rmaciel.assetmanagement.model.endpoints.forms;

import com.rmaciel.academy.core.models.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class ModelForm {

    @NotEmpty @NotNull @Length(min = 2, max = 30)
    private String title;

    @NotEmpty @NotNull @Length(min = 2, max = 30)
    private String brand;

    @NotEmpty @NotNull @Length(min = 2, max = 30)
    private String type;

    public Model build(){
        return new Model(title, brand, type);
    }

    public Model updateFrom(Model model) {
        model.setTitle(title);
        model.setBrand(brand);
        model.setType(type);

        return model;
    }
}

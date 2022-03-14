package com.rmaciel.assetmanagement.model.endpoints.forms;

import com.rmaciel.academy.core.models.Model;
import com.rmaciel.academy.core.validations.constraints.unique.Unique;
import com.rmaciel.academy.core.validations.constraints.unique.services.ModelUniqueService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class ModelCreateForm {

    @NotBlank
    @Length(max = 30)
    @Unique(message = "Já existe um modelo com o mesmo nome",
            service = ModelUniqueService.class,
            fieldName = "title")
    private String title;

    @NotBlank
    @Length(max = 30)
    private String brand;

    @NotBlank
    @Length(max = 50)
    private String type;

    public Model build(){
        return new Model(title, brand, type);
    }
}

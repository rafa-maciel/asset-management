package com.rmaciel.assetmanagement.model.endpoints.forms;

import com.rmaciel.academy.core.models.Model;
import com.rmaciel.academy.core.specifications.ModelSpecifications;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import static com.rmaciel.academy.core.specifications.ModelSpecifications.*;

@Data
@AllArgsConstructor
@Slf4j
public class ModelSearchForm {
    private String title;
    private String brand;
    private String type;

    public Specification<Model> buildSpecs() {
        Specification<Model> specs = likeTitle(title)
                .and(likeBrand(brand))
                .and(likeType(type));

        return specs;
    }


}

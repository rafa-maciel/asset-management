package com.rmaciel.assetmanagement.model.endpoints;

import com.rmaciel.academy.core.models.Model;
import com.rmaciel.academy.core.repositories.ModelRepository;
import com.rmaciel.assetmanagement.model.endpoints.forms.ModelCreateForm;
import com.rmaciel.assetmanagement.model.endpoints.forms.ModelSearchForm;
import com.rmaciel.assetmanagement.model.endpoints.forms.ModelUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/models")
public class ModelController {

    private final ModelRepository modelRepository;

    public ModelController(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    private Model findModelOrNull(Long id) {
        Optional<Model> optionalModel = modelRepository.findById(id);
        if (optionalModel.isEmpty())
            return null;

        return optionalModel.get();
    }

    @PostMapping
    public ResponseEntity<Model> create(@RequestBody @Valid ModelCreateForm form) {
        Model model = modelRepository.save(form.build());
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Model> update(@PathVariable Long id, @RequestBody @Valid ModelUpdateForm form) {
        Model savedModel = findModelOrNull(id);
        if (savedModel == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        Model model = modelRepository.save(form.updateFrom(savedModel));
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Model model = findModelOrNull(id);
        if (model == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        modelRepository.delete(model);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Model> details(@PathVariable Long id) {
        Model model = findModelOrNull(id);
        if (model == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.ok(model);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Model>> search(Pageable pageable, @Valid ModelSearchForm form) {
        Specification<Model> specs = form.buildSpecs();
        Page<Model> page = modelRepository.findAll(specs, pageable);

        return ResponseEntity.ok(page);
    }


}

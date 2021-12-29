package com.rmaciel.assetmanagement.contract.endpoints;

import com.rmaciel.academy.core.models.Contract;
import com.rmaciel.academy.core.models.Location;
import com.rmaciel.academy.core.repositories.ContractRepository;
import com.rmaciel.assetmanagement.contract.endpoints.forms.ContractForm;
import com.rmaciel.assetmanagement.contract.endpoints.forms.ContractSearchForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/contracts")
@Slf4j
public class ContractController {
    private final ContractRepository contractRepository;

    protected ContractController(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    private Contract findOrNull(Long id) {
        Optional<Contract> contractOptional = contractRepository.findById(id);
        if (contractOptional.isEmpty())
                return null;

        return contractOptional.get();
    }

    @PostMapping
    public ResponseEntity<Contract> create(@RequestBody @Valid ContractForm form) {
        Contract contract = contractRepository.save(form.build());
        return ResponseEntity.status(HttpStatus.CREATED).body(contract);
    }

    @PutMapping("/{contractId}")
    public ResponseEntity<Contract> update(@PathVariable("contractId") Long id, @RequestBody @Valid ContractForm form) {
        Contract contract = findOrNull(id);
        if (contract == null) return ResponseEntity.badRequest().build();

        Contract updatedContract = contractRepository.save(form.updateFrom(contract));
        return ResponseEntity.ok(updatedContract);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Contract>> search(Pageable pageable, @Valid ContractSearchForm form) {
        Specification<Contract> specs = form.buildSpecs();
        Page<Contract> page = contractRepository.findAll(specs, pageable);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contract> details(@PathVariable Long id) {
        Contract contract = findOrNull(id);
        if (contract == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.ok(contract);
    }

    @DeleteMapping("/{contractId}")
    public ResponseEntity<?> delete(@PathVariable("contractId") Long id) {
        Contract contract = findOrNull(id);
        log.info(contract.getVendor());
        if (contract == null) return ResponseEntity.badRequest().build();

        contractRepository.delete(contract);
        return ResponseEntity.ok().build();
    }
}

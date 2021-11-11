package com.rmaciel.assetmanagement.asset.endpoints;

import com.rmaciel.academy.core.models.Asset;
import com.rmaciel.academy.core.models.Contract;
import com.rmaciel.academy.core.repositories.AssetRepository;
import com.rmaciel.academy.core.repositories.ContractRepository;
import com.rmaciel.assetmanagement.asset.endpoints.forms.ContractForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/assets/{assetId}/contract")
@Slf4j
public class ContractController {
    private final ContractRepository contractRepository;
    private final AssetRepository assetRepository;

    protected ContractController(ContractRepository contractRepository, AssetRepository assetRepository) {
        this.contractRepository = contractRepository;
        this.assetRepository = assetRepository;
    }

    private Contract findOrNull(Long id) {
        Optional<Contract> contractOptional = contractRepository.findById(id);
        if (contractOptional.isEmpty())
                return null;

        return contractOptional.get();
    }

    private Asset findAssetOrNull(Long id) {
        Optional<Asset> assetOptional = assetRepository.findById(id);
        if (assetOptional.isEmpty())
            return null;

        return assetOptional.get();
    }

    @PostMapping
    public ResponseEntity<Contract> create(@PathVariable Long assetId, @RequestBody @Valid ContractForm form) {
        Asset asset = findAssetOrNull(assetId);
        if (asset == null) return ResponseEntity.badRequest().build();

        Contract contract = contractRepository.save(form.build(asset));
        return ResponseEntity.status(HttpStatus.CREATED).body(contract);
    }

    @PutMapping("/{contractId}")
    public ResponseEntity<Contract> update(@PathVariable("contractId") Long id, @RequestBody @Valid ContractForm form) {
        Contract contract = findOrNull(id);
        if (contract == null) return ResponseEntity.badRequest().build();

        Contract updatedContract = contractRepository.save(form.updateFrom(contract));
        return ResponseEntity.ok(updatedContract);
    }

    @GetMapping
    public ResponseEntity<Contract> listAll(@PathVariable Long assetId) {
        Contract contracts = contractRepository.findByAssetId(assetId);
        return ResponseEntity.ok(contracts);
    }

    @DeleteMapping("/{contractId}")
    public ResponseEntity<?> delete(@PathVariable("contractId") Long id) {
        Contract contract = findOrNull(id);
        log.info(contract.getVendor());
        if (contract == null) return ResponseEntity.badRequest().build();

        contractRepository.delete(contract);
        log.info(findOrNull(id).getVendor());
        return ResponseEntity.ok().build();
    }
}

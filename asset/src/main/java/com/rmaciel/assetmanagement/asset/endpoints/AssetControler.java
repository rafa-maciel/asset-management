package com.rmaciel.assetmanagement.asset.endpoints;

import com.rmaciel.academy.core.models.Asset;
import com.rmaciel.academy.core.models.Note;
import com.rmaciel.academy.core.models.UserAccount;
import com.rmaciel.academy.core.repositories.*;
import com.rmaciel.assetmanagement.asset.endpoints.forms.AssetForm;
import com.rmaciel.assetmanagement.asset.endpoints.forms.AssetImportForm;
import com.rmaciel.assetmanagement.asset.endpoints.forms.AssetSearchForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/assets")
@Slf4j
public class AssetControler {

    private final AssetRepository assetRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final ModelRepository modelRepository;
    private final ContractRepository contractRepository;
    private final InvoiceRepository invoiceRepository;
    private final UserAccountRepository userAccountRepository;
    private final NoteRepository noteRepository;

    protected AssetControler(AssetRepository assetRepository,
                             UserRepository userRepository,
                             LocationRepository locationRepository,
                             ModelRepository modelRepository,
                             ContractRepository contractRepository,
                             InvoiceRepository invoiceRepository,
                             UserAccountRepository userAccountRepository,
                             NoteRepository noteRepository) {
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
        this.modelRepository = modelRepository;
        this.contractRepository = contractRepository;
        this.invoiceRepository = invoiceRepository;
        this.userAccountRepository = userAccountRepository;
        this.noteRepository = noteRepository;
    }

    private Asset findAssetOrNull(Long id) {
        Optional<Asset> assetOptional = assetRepository.findById(id);
        if (assetOptional.isEmpty())
            return null;

        return assetOptional.get();
    }

    private UserAccount findAuthorOrNull(Authentication auth) {
        UserDetails userAuth = (UserDetails) auth.getPrincipal();
        Optional<UserAccount> optionalUser = userAccountRepository.findByEmail(userAuth.getUsername());

        return optionalUser.orElse(null);
    }

    @PostMapping
    public ResponseEntity<Asset> create(@RequestBody @Valid AssetForm form) {
        Asset asset = assetRepository.save(
                form.build(userRepository, locationRepository, modelRepository, contractRepository, invoiceRepository));

        return ResponseEntity.status(HttpStatus.CREATED).body(asset);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asset> update(@PathVariable Long id, @RequestBody @Valid AssetForm form, Authentication auth) {
        Asset savedAsset = findAssetOrNull(id);
        if (savedAsset == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        log.info("Creating update note");
        String savedAssetString = savedAsset.toString();
        log.info(savedAssetString);


        Asset updatedAsset = form.updateFrom(savedAsset, userRepository, locationRepository,
                modelRepository, contractRepository, invoiceRepository);

        UserAccount author = findAuthorOrNull(auth);
        noteRepository.save(new Note(updatedAsset, "Atualidado de: " + savedAssetString, author));
        noteRepository.save(new Note(updatedAsset, "Para: " + updatedAsset.toString(), author));

        Asset asset = assetRepository.save(updatedAsset);
        return ResponseEntity.ok(asset);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Asset savedAsset = findAssetOrNull(id);
        if (savedAsset == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        assetRepository.delete(savedAsset);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> details(@PathVariable Long id) {
        Asset asset = findAssetOrNull(id);
        if (asset == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.ok(asset);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Asset>> search(Pageable pageable, @Valid AssetSearchForm form) {
        Specification<Asset> specs = form.buildSpecs();
        Page<Asset> page = assetRepository.findAll(specs, pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping("/import")
    public ResponseEntity<?> importAssets(@RequestBody @Valid List<AssetImportForm> forms) {
        if (forms != null) {
            forms.forEach(form -> {
                if (form != null) {
                    assetRepository.save(form.build());
                }
            });
        }

        return ResponseEntity.ok().build();
    }
}

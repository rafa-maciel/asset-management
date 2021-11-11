package com.rmaciel.assetmanagement.asset.endpoints;

import com.rmaciel.academy.core.models.Asset;
import com.rmaciel.academy.core.models.File;
import com.rmaciel.academy.core.repositories.AssetRepository;
import com.rmaciel.academy.core.repositories.FileRepository;
import com.rmaciel.academy.core.services.FileManagerService;
import com.rmaciel.assetmanagement.asset.endpoints.forms.FileForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Optional;

@RestController
@RequestMapping("/assets/{assetId}/files")
@Slf4j
public class FileController {

    private final FileRepository fileRepository;
    private final AssetRepository assetRepository;
    private final FileManagerService fileService;

    protected FileController(FileRepository fileRepository, AssetRepository assetRepository, FileManagerService fileService){
        this.fileRepository = fileRepository;
        this.assetRepository = assetRepository;
        this.fileService = fileService;
    }

    private Asset findAssetOrNull(Long id) {
        Optional<Asset> assetOptional = assetRepository.findById(id);
        if (assetOptional.isEmpty())
            return null;

        return assetOptional.get();
    }

    private File findFileOrNull(Long id) {
        Optional<File> fileOptional = fileRepository.findById(id);
        if (fileOptional.isEmpty())
            return null;

        return fileOptional.get();
    }

    @PostMapping(consumes = { "multipart/form-data" } )
    public ResponseEntity<File> create(@ModelAttribute FileForm form, @PathVariable Long assetId) throws IOException {
        Asset asset = findAssetOrNull(assetId);
        if (asset == null) return ResponseEntity.badRequest().build();

        String absoluteFilename = fileService.save(form.getFile().getOriginalFilename(), form.getFile().getBytes());
        File file = fileRepository.save(form.build(asset, absoluteFilename));
        return ResponseEntity.status(HttpStatus.CREATED).body(file);
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity<?> delete(@PathVariable Long fileId) throws IOException {
        File file = findFileOrNull(fileId);
        if (file == null) return ResponseEntity.badRequest().build();

        try {
            fileService.deleteFile(file.getUri());
        } catch (NoSuchFileException ex) {
            log.info("No file found in disk, register of file will be deleted.");
        }

        fileRepository.delete(file);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Iterable<File>> findAll(@PathVariable Long assetId) {
        Iterable<File> files = fileRepository.findAllByAssetId(assetId);
        return ResponseEntity.ok(files);
    }

}

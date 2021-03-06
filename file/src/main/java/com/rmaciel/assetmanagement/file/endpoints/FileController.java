package com.rmaciel.assetmanagement.file.endpoints;

import com.rmaciel.academy.core.models.File;
import com.rmaciel.academy.core.repositories.AssetRepository;
import com.rmaciel.academy.core.repositories.ContractRepository;
import com.rmaciel.academy.core.repositories.FileRepository;
import com.rmaciel.academy.core.repositories.InvoiceRepository;
import com.rmaciel.academy.core.services.FileManagerService;
import com.rmaciel.academy.core.utils.StorageFile;
import com.rmaciel.assetmanagement.file.endpoints.forms.FileForm;
import com.rmaciel.assetmanagement.file.utils.MediaTypeForFilename;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Optional;

@RestController
@RequestMapping("/files")
@Slf4j
public class FileController {
    private final FileRepository fileRepository;
    private final FileManagerService fileManagerService;
    private final AssetRepository assetRepository;
    private final ContractRepository contractRepository;
    private final InvoiceRepository invoiceRepository;
    private final ServletContext servletContext;

    protected FileController(FileRepository fileRepository,
                             FileManagerService fileManagerService,
                             AssetRepository assetRepository,
                             ContractRepository contractRepository,
                             InvoiceRepository invoiceRepository,
                             ServletContext context) {
        this.fileRepository = fileRepository;
        this.fileManagerService = fileManagerService;
        this.assetRepository = assetRepository;
        this.contractRepository = contractRepository;
        this.invoiceRepository = invoiceRepository;
        servletContext = context;
    }

    private File findOrNull(Long id) {
        Optional<File> fileOptional = fileRepository.findById(id);
        if (fileOptional.isEmpty())
                return null;

        return fileOptional.get();
    }

    @PostMapping(consumes = { "multipart/form-data" } )
    public ResponseEntity<File> create(@ModelAttribute FileForm form) throws IOException {
        String absoluteFilename = fileManagerService.saveDefaultName(form.getFileExtension(), form.getBytes());

        File file = fileRepository.save(form.build(absoluteFilename, assetRepository, contractRepository, invoiceRepository));
        return ResponseEntity.status(HttpStatus.CREATED).body(file);
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity<?> delete(@PathVariable Long fileId) throws IOException {
        File file = findOrNull(fileId);
        if (file == null) return ResponseEntity.badRequest().build();

        try {
            fileManagerService.deleteFile(file.getUri());
        } catch (NoSuchFileException ex) {
            log.info("No file found in disk, register of file will be deleted.");
        }

        fileRepository.delete(file);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/asset/{assetId}")
    public ResponseEntity<Iterable<File>> findAllByAsset(@PathVariable Long assetId) {
        Iterable<File> files = fileRepository.findAllByAssetId(assetId);
        return ResponseEntity.ok(files);
    }

    @GetMapping("/contract/{contractId}")
    public ResponseEntity<Iterable<File>> findAllByContract(@PathVariable Long contractId) {
        Iterable<File> files = fileRepository.findAllByContractId(contractId);
        return ResponseEntity.ok(files);
    }

    @GetMapping("/invoice/{invoiceId}")
    public ResponseEntity<Iterable<File>> findAllByInvoice(@PathVariable Long invoiceId) {
        Iterable<File> files = fileRepository.findAllByInvoiceId(invoiceId);
        return ResponseEntity.ok(files);
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<Resource> download(@PathVariable Long fileId) throws IOException {
        Optional<File> optionalFile = fileRepository.findById(fileId);
        if (optionalFile.isEmpty()) return ResponseEntity.badRequest().build();

        File file = optionalFile.get();

        StorageFile storageFile = fileManagerService.storageFile(file.getUri());
        MediaType mediaType = MediaTypeForFilename.getType(servletContext, storageFile.getFileName());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

        return ResponseEntity.ok()
                .contentType(mediaType)
                .headers(headers)
                .body(storageFile.getResource());
    }
}

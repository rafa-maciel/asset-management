package com.rmaciel.assetmanagement.file.endpoints.forms;

import com.rmaciel.academy.core.models.Asset;
import com.rmaciel.academy.core.models.File;
import com.rmaciel.academy.core.repositories.AssetRepository;
import com.rmaciel.academy.core.repositories.ContractRepository;
import com.rmaciel.academy.core.repositories.InvoiceRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@AllArgsConstructor
@Getter
public class FileForm {
    private String name;
    private String note;
    private MultipartFile file;
    private Long assetId;
    private Long contractId;
    private Long invoiceId;

    public File build(String pathname, AssetRepository assetRepository, ContractRepository contractRepository, InvoiceRepository invoiceRepository) {
        File fileCreated = new File(name, note, pathname);

        if (assetId != null && assetId > 0) {
            assetRepository.findById(assetId)
                    .ifPresent(fileCreated::setAsset);
        }

        if (contractId != null && contractId > 0) {
            contractRepository.findById(contractId)
                    .ifPresent(fileCreated::setContract);
        }

        if (invoiceId != null && invoiceId > 0) {
            invoiceRepository.findById(invoiceId)
                    .ifPresent(fileCreated::setInvoice);
        }

        return fileCreated;
    }
}

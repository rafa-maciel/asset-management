package com.rmaciel.assetmanagement.asset.endpoints.forms;

import com.rmaciel.academy.core.models.*;
import com.rmaciel.academy.core.repositories.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class AssetForm {
    @NotNull
    private Long ownerId;
    @NotNull
    private Long locationId;
    @NotNull
    private Long modelId;

    private Long contractId;
    private Long invoiceId;

    private String hostname;
    private String serialNumber;
    private String tag;
    private String imei;

    @Length(max = 60)
    private String companyIdentification;

    @NotNull
    private AssetStatus status;

    @Length(max = 60)
    private String chipIdentification;

    @Length(max = 60)
    private String lineIdentification;

    public Asset build(UserRepository userRepository,
                       LocationRepository locationRepository,
                       ModelRepository modelRepository,
                       ContractRepository contractRepository,
                       InvoiceRepository invoiceRepository) {

        User owner = userRepository.findById(ownerId).get();
        Location location = locationRepository.findById(locationId).get();
        Model model = modelRepository.findById(modelId).get();

        Asset asset = new Asset(owner, location, model, companyIdentification, status);
        asset.setChipIdentification(this.chipIdentification);
        asset.setLineIdentification(this.lineIdentification);

        if (contractId != null && contractId > 0)
            asset.setContract(contractRepository.findById(contractId).get());

        if (invoiceId != null && invoiceId > 0)
            asset.setInvoice(invoiceRepository.findById(invoiceId).get());

        asset.setHostname(hostname);
        asset.setSerialNumber(serialNumber);
        asset.setTag(tag);
        asset.setImei(imei);

        return asset;
    }

    public Asset updateFrom(Asset asset,
                            UserRepository userRepository,
                            LocationRepository locationRepository,
                            ModelRepository modelRepository,
                            ContractRepository contractRepository,
                            InvoiceRepository invoiceRepository) {

        User owner = userRepository.findById(ownerId).get();
        Location location = locationRepository.findById(locationId).get();
        Model model = modelRepository.findById(modelId).get();
        Contract contract = contractRepository.findById(contractId).get();
        Invoice invoice = invoiceRepository.findById(invoiceId).get();

        asset.setOwner(owner);
        asset.setLocation(location);
        asset.setModel(model);
        asset.setContract(contract);
        asset.setInvoice(invoice);
        asset.setStatus(this.status);
        asset.setCompanyIdentification(this.companyIdentification);
        asset.setChipIdentification(this.chipIdentification);
        asset.setLineIdentification(this.lineIdentification);
        asset.setHostname(this.hostname);
        asset.setSerialNumber(this.serialNumber);
        asset.setTag(this.tag);
        asset.setImei(this.imei);

        return asset;
    }
}

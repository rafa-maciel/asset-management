package com.rmaciel.assetmanagement.asset.endpoints.forms;

import com.rmaciel.academy.core.models.*;
import com.rmaciel.academy.core.repositories.*;
import com.rmaciel.academy.core.validations.constraints.unique.Unique;
import com.rmaciel.academy.core.validations.constraints.unique.services.AssetUniqueService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Getter
public class AssetCreateForm {
    @NotNull
    private Long ownerId;
    @NotNull
    private Long locationId;
    @NotNull
    private Long modelId;

    private Long contractId;
    private Long invoiceId;

    @Unique(message = "Já existe um dispositivo com este hostname",
            service = AssetUniqueService.class,
            fieldName = "hostname")
    @Length(max = 30)
    private String hostname;


    @Unique(message = "Já existe um dispositivo com este número de série",
            service = AssetUniqueService.class,
            fieldName = "serialNumber")
    @Length(max = 50)
    private String serialNumber;

    @Unique(message = "Já existe um dispositivo com esta tag",
           service = AssetUniqueService.class,
           fieldName = "tag")
    @Length(max = 10)
    private String tag;

    @Unique(message = "Já existe um dispositivo com este imei",
           service = AssetUniqueService.class,
           fieldName = "imei")
    @Max(value = 999999999)
    @Min(value = 999999999)
    private Long imei;

    @Unique(message = "Já existe um dispositivo com esta identificação",
           service = AssetUniqueService.class,
           fieldName = "companyIdentification")
    @Max(value = 999999)
    private Integer companyIdentification;

    @NotNull
    private AssetStatus status;

    @Unique(message = "Já existe um dispositivo com este número de chip",
            service = AssetUniqueService.class,
            fieldName = "chipIdentification")
    @Max(value = 999999999)
    private Long chipIdentification;

    @Unique(message = "Já existe um dispositivo com esta linha",
            service = AssetUniqueService.class,
            fieldName = "lineIdentification")
    @Length(max = 18)
    private String lineIdentification;

    private LocalDate endOfWarranty;

    public Asset build(UserRepository userRepository,
                       LocationRepository locationRepository,
                       ModelRepository modelRepository,
                       ContractRepository contractRepository,
                       InvoiceRepository invoiceRepository) {

        User owner = findOwner(userRepository, ownerId);
        Location location = findLocation(locationRepository, locationId);
        Model model = findModel(modelRepository, modelId);

        Asset asset = new Asset(owner, location, model, status);
        asset.setCompanyIdentification(this.companyIdentification);
        asset.setChipIdentification(this.chipIdentification);
        asset.setLineIdentification(this.lineIdentification);
        asset.setHostname(hostname);
        asset.setSerialNumber(serialNumber);
        asset.setTag(tag);
        asset.setImei(imei);
        asset.setEndOfWarranty(endOfWarranty);

        Contract contract = findContract(contractRepository, contractId);
        if (contract != null) asset.setContract(contract);

        Invoice invoice = findInvoice(invoiceRepository, invoiceId);
        if (invoice != null) asset.setInvoice(invoice);

        return asset;
    }


    private User findOwner(UserRepository repository, Long id) {
        if (id == null || id < 1) throw new UnsupportedOperationException();

        Optional<User> finder = repository.findById(id);
        if (finder.isEmpty()) throw new NoSuchElementException();

        return finder.get();
    }

    private Location findLocation(LocationRepository repository, Long id) {
        if (id == null || id < 1) throw new UnsupportedOperationException();

        Optional<Location> finder = repository.findById(id);
        if (finder.isEmpty()) throw new NoSuchElementException();

        return finder.get();
    }

    private Model findModel(ModelRepository repository, Long id) {
        if (id == null || id < 1) throw new UnsupportedOperationException();

        Optional<Model> finder = repository.findById(id);
        if (finder.isEmpty()) throw new NoSuchElementException();

        return finder.get();
    }

    private Contract findContract(ContractRepository repository, Long id) {
        if (id == null || id < 1) return null;

        Optional<Contract> finder = repository.findById(id);
        if (finder.isEmpty()) return null;

        return finder.get();
    }

    private Invoice findInvoice(InvoiceRepository repository, Long id) {
        if (id == null || id < 1) return null;

        Optional<Invoice> finder = repository.findById(id);
        if (finder.isEmpty()) return null;

        return finder.get();
    }
}

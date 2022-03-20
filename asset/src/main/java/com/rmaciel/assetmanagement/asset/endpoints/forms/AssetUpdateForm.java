package com.rmaciel.assetmanagement.asset.endpoints.forms;

import com.rmaciel.academy.core.models.*;
import com.rmaciel.academy.core.repositories.*;
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
public class AssetUpdateForm {
    @NotNull
    private Long ownerId;
    @NotNull
    private Long locationId;
    @NotNull
    private Long modelId;

    private Long contractId;
    private Long invoiceId;

    @Length(max = 30)
    private String hostname;

    @Length(max = 50)
    private String serialNumber;

    @Length(max = 10)
    private String tag;

    @Max(value = 999999999)
    @Min(value = 999999999)
    private Long imei;

    @Max(value = 999999)
    private Integer companyIdentification;

    @NotNull
    private AssetStatus status;

    @Max(value = 999999999)
    private Long chipIdentification;

    @Length(max = 18)
    private String lineIdentification;

    private LocalDate endOfWarranty;

    public Asset updateFrom(Asset asset,
                            UserRepository userRepository,
                            LocationRepository locationRepository,
                            ModelRepository modelRepository,
                            ContractRepository contractRepository,
                            InvoiceRepository invoiceRepository) {

        User owner = findOwner(userRepository, ownerId);
        Location location = findLocation(locationRepository, locationId);
        Model model = findModel(modelRepository, modelId);

        Contract contract = findContract(contractRepository, contractId);
        Invoice invoice = findInvoice(invoiceRepository, invoiceId);

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
        asset.setEndOfWarranty(endOfWarranty);

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

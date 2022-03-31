package com.rmaciel.assetmanagement.asset.endpoints.forms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rmaciel.academy.core.dto.FieldErrorMessageDTO;
import com.rmaciel.academy.core.dto.InvalidAssetDataDTO;
import com.rmaciel.academy.core.models.*;
import com.rmaciel.academy.core.repositories.*;
import com.rmaciel.academy.core.validations.constraints.exists.Exists;
import com.rmaciel.academy.core.validations.constraints.exists.services.*;
import com.rmaciel.academy.core.validations.constraints.unique.Unique;
import com.rmaciel.academy.core.validations.constraints.unique.services.AssetUniqueService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ValidAssetForm {

    @Exists(message = "Responsável não encontrado com este RE",
            service = UserExistsService.class,
            fieldName = "re")
    private Integer ownerRe;

    @Exists(message = "Localização não encontrada com este Titulo",
            service = LocationExistsService.class,
            fieldName = "title")
    private String locationTitle;

    @Exists(message = "Modelo não encontrado com este Titulo",
            service = ModelExistsService.class,
            fieldName = "title")
    private String modelTitle;


    @Exists(message = "Contracto não encontrado com este Número",
            service = ContractExistsService.class,
            fieldName = "number")
    private String contractNumber;

    @Exists(message = "Nota fiscal não encontrada com este Número",
            service = InvoiceExistsService.class,
            fieldName = "number")
    private Integer invoiceNumber;

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
    @Length(max = 20)
    private String imei;

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
    @Length(max = 30)
    private String chipIdentification;

    @Unique(message = "Já existe um dispositivo com esta linha",
            service = AssetUniqueService.class,
            fieldName = "lineIdentification")
    @Length(max = 18)
    private String lineIdentification;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate endOfWarranty;

    public InvalidAssetDataDTO buildInvalidData(List<FieldErrorMessageDTO> fieldErrors) {
        return new InvalidAssetDataDTO(ownerRe, locationTitle, modelTitle, contractNumber, invoiceNumber, hostname,
                serialNumber, tag, imei, companyIdentification, status, chipIdentification, lineIdentification,
                endOfWarranty, fieldErrors);
    }

    public Asset build(UserRepository userRepository,
                       LocationRepository locationRepository,
                       ModelRepository modelRepository,
                       ContractRepository contractRepository,
                       InvoiceRepository invoiceRepository) {

        User owner = findOwner(userRepository, ownerRe);
        Location location = findLocation(locationRepository, locationTitle);
        Model model = findModel(modelRepository, modelTitle);

        Asset asset = new Asset(owner, location, model, status);
        asset.setCompanyIdentification(this.companyIdentification);
        asset.setChipIdentification(this.chipIdentification);
        asset.setLineIdentification(this.lineIdentification);
        asset.setHostname(hostname);
        asset.setSerialNumber(serialNumber);
        asset.setTag(tag);
        asset.setImei(imei);
        asset.setEndOfWarranty(endOfWarranty);

        Contract contract = findContract(contractRepository, contractNumber);
        if (contract != null) asset.setContract(contract);

        Invoice invoice = findInvoice(invoiceRepository, invoiceNumber);
        if (invoice != null) asset.setInvoice(invoice);

        return asset;
    }


    private User findOwner(UserRepository repository, Integer re) {
        if (re == null || re < 1) throw new UnsupportedOperationException();

        Optional<User> finder = repository.findByRe(re);
        if (finder.isEmpty()) throw new NoSuchElementException();

        return finder.get();
    }

    private Location findLocation(LocationRepository repository, String title) {
        if (title == null || title.isEmpty()) throw new UnsupportedOperationException();

        Optional<Location> finder = repository.findByTitle(title);
        if (finder.isEmpty()) throw new NoSuchElementException();

        return finder.get();
    }

    private Model findModel(ModelRepository repository, String title) {
        if (title == null || title.isEmpty()) throw new UnsupportedOperationException();

        Optional<Model> finder = repository.findByTitle(title);
        if (finder.isEmpty()) throw new NoSuchElementException();

        return finder.get();
    }

    private Contract findContract(ContractRepository repository, String number) {
        if (number == null || number.isEmpty()) return null;

        Optional<Contract> finder = repository.findByNumber(number);
        if (finder.isEmpty()) return null;

        return finder.get();
    }

    private Invoice findInvoice(InvoiceRepository repository, Integer number) {
        if (number == null || number < 1) return null;

        Optional<Invoice> finder = repository.findByNumber(number);
        if (finder.isEmpty()) return null;

        return finder.get();
    }
}

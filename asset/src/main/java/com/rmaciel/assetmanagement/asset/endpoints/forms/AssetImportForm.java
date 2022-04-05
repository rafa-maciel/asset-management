package com.rmaciel.assetmanagement.asset.endpoints.forms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rmaciel.academy.core.models.*;
import com.rmaciel.academy.core.validations.constraints.unique.Unique;
import com.rmaciel.academy.core.validations.constraints.unique.services.AssetUniqueService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AssetImportForm {
    @NotNull
    private User owner;
    @NotNull
    private Location location;
    @NotNull
    private Model model;

    private Contract contract;
    private Invoice invoice;

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

    public Asset build() {

        Asset asset = new Asset(owner, location, model, status);
        asset.setCompanyIdentification(this.companyIdentification);
        asset.setChipIdentification(this.chipIdentification);
        asset.setLineIdentification(this.lineIdentification);
        asset.setHostname(hostname);
        asset.setSerialNumber(serialNumber);
        asset.setTag(tag);
        asset.setImei(imei);
        asset.setEndOfWarranty(endOfWarranty);

        if (contract != null) asset.setContract(contract);
        if (invoice != null) asset.setInvoice(invoice);

        return asset;
    }
}

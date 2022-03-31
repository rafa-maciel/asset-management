package com.rmaciel.academy.core.dto;

import com.rmaciel.academy.core.models.AssetStatus;
import com.rmaciel.academy.core.validations.constraints.exists.Exists;
import com.rmaciel.academy.core.validations.constraints.exists.services.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
public class InvalidAssetDataDTO {

    private Integer ownerRe;
    private String locationTitle;
    private String modelTitle;
    private String contractNumber;
    private Integer invoiceNumber;
    private String hostname;
    private String serialNumber;
    private String tag;
    private String imei;
    private Integer companyIdentification;
    private AssetStatus status;
    private String chipIdentification;
    private String lineIdentification;
    private LocalDate endOfWarranty;
    private List<FieldErrorMessageDTO> fieldErrors;
}

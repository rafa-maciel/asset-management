package com.rmaciel.academy.core.validations.constraints.unique.impl;

import com.rmaciel.academy.core.repositories.AssetRepository;
import com.rmaciel.academy.core.validations.constraints.unique.services.AssetUniqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetUniqueServiceImpl implements AssetUniqueService {

    @Autowired
    private AssetRepository repository;

    private static final String FIELD_CHIP_IDENTIFICATION = "chipidentification";
    private static final String FIELD_COMPANY_IDENTIFICATION = "companyidentification";
    private static final String FIELD_HOSTNAME = "hostname";
    private static final String FIELD_LINE_IDENTIFICATION = "lineidentification";
    private static final String FIELD_SERIAL_NUMBER = "serialnumber";
    private static final String FIELD_TAG = "tag";
    private static final String FIELD_IMEI = "imei";

    @Override
    public boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException {
        if (fieldName == null) throw new UnsupportedOperationException();
        if (value == null) return false;

        switch (fieldName.toLowerCase()) {
            case FIELD_CHIP_IDENTIFICATION:
                return repository.existsByChipIdentification(value.toString());

            case FIELD_COMPANY_IDENTIFICATION:
                return repository.existsByCompanyIdentification(Integer.parseInt(value.toString()));

            case FIELD_HOSTNAME:
                return repository.existsByHostname(value.toString());

            case FIELD_LINE_IDENTIFICATION:
                return repository.existsByLineIdentification(value.toString());

            case FIELD_SERIAL_NUMBER:
                return repository.existsBySerialNumber(value.toString());

            case FIELD_TAG:
                return repository.existsByTag(value.toString());

            case FIELD_IMEI:
                return repository.existsByImei(value.toString());


            default:
                throw new UnsupportedOperationException("Field name not supported");
        }
    }
}

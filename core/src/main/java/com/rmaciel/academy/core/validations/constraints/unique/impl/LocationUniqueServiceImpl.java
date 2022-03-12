package com.rmaciel.academy.core.validations.constraints.unique.impl;

import com.rmaciel.academy.core.repositories.LocationRepository;
import com.rmaciel.academy.core.validations.constraints.unique.services.LocationUniqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("locationTitle")
public class LocationUniqueServiceImpl implements LocationUniqueService {

    @Autowired
    private LocationRepository repository;

    @Override
    public boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException {
        if (fieldName == null) throw new UnsupportedOperationException();
        if (!fieldName.equals("title")) throw new UnsupportedOperationException("Field name not supported");

        if (value == null) return false;

        return this.repository.existsByTitle(value.toString());
    }
}

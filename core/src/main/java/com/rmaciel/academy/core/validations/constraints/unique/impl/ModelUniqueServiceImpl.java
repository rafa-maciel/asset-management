package com.rmaciel.academy.core.validations.constraints.unique.impl;

import com.rmaciel.academy.core.repositories.ModelRepository;
import com.rmaciel.academy.core.validations.constraints.unique.services.ModelUniqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelUniqueServiceImpl implements ModelUniqueService {

    @Autowired
    private ModelRepository repository;

    @Override
    public boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException {
        if (fieldName == null) throw new UnsupportedOperationException();
        if (!fieldName.equals("title")) throw new UnsupportedOperationException("Field name not supported");

        if (value == null) return false;

        return repository.existsByTitle(value.toString());
    }
}

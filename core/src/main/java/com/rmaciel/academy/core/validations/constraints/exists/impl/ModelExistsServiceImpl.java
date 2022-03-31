package com.rmaciel.academy.core.validations.constraints.exists.impl;

import com.rmaciel.academy.core.repositories.ModelRepository;
import com.rmaciel.academy.core.validations.constraints.exists.services.ModelExistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelExistsServiceImpl implements ModelExistsService {

    @Autowired
    private ModelRepository repository;

    @Override
    public boolean existsBy(Object value, String fieldName) throws UnsupportedOperationException {
        if (fieldName == null) throw new UnsupportedOperationException();
        if (!fieldName.equals("title")) throw new UnsupportedOperationException("Field name not supported");

        if (value == null) return false;

        return repository.existsByTitle(value.toString());
    }
}

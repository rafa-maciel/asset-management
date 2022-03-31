package com.rmaciel.academy.core.validations.constraints.exists.impl;

import com.rmaciel.academy.core.repositories.ContractRepository;
import com.rmaciel.academy.core.validations.constraints.unique.services.ContractUniqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractUniqueServiceImpl implements ContractUniqueService {

    @Autowired
    private ContractRepository repository;

    @Override
    public boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException {
        if (fieldName == null) throw new UnsupportedOperationException();
        if (!fieldName.equals("number")) throw new UnsupportedOperationException("Field name not supported");

        if (value == null) return false;

        return repository.existsByNumber(value.toString());
    }
}

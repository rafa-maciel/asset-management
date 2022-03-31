package com.rmaciel.academy.core.validations.constraints.exists.impl;

import com.rmaciel.academy.core.repositories.ContractRepository;
import com.rmaciel.academy.core.validations.constraints.exists.services.ContractExistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractExistsServiceImpl implements ContractExistsService {

    @Autowired
    private ContractRepository repository;

    @Override
    public boolean existsBy(Object value, String fieldName) throws UnsupportedOperationException {
        if (fieldName == null) throw new UnsupportedOperationException();
        if (!fieldName.equals("number")) throw new UnsupportedOperationException("Field name not supported");

        // Contract is not required, if there is nothing to valid it is ok
        if (value == null) return true;

        return repository.existsByNumber(value.toString());
    }
}

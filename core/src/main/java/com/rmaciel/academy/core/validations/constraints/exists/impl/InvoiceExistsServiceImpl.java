package com.rmaciel.academy.core.validations.constraints.exists.impl;

import com.rmaciel.academy.core.repositories.InvoiceRepository;
import com.rmaciel.academy.core.validations.constraints.exists.services.InvoiceExistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceExistsServiceImpl implements InvoiceExistsService {

    @Autowired
    private InvoiceRepository repository;

    @Override
    public boolean existsBy(Object value, String fieldName) throws UnsupportedOperationException {
        if (fieldName == null) throw new UnsupportedOperationException();
        if (!fieldName.equals("number")) throw new UnsupportedOperationException("Field name not supported");

        // Invoice is not required, if there is nothing to valid it is ok
        if (value == null) return true;

        return repository.existsByNumber((Integer) value);
    }
}

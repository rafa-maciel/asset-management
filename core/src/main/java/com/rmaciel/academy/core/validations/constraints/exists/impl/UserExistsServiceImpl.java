package com.rmaciel.academy.core.validations.constraints.exists.impl;

import com.rmaciel.academy.core.repositories.UserRepository;
import com.rmaciel.academy.core.validations.constraints.unique.services.UserUniqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUniqueServiceImpl implements UserUniqueService {

    @Autowired
    private UserRepository repository;

    private static final String FIELD_NAME = "name";
    private static final String FIELD_RE = "re";

    @Override
    public boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException {
        if (fieldName == null) throw new UnsupportedOperationException();
        if (value == null) return false;

        switch (fieldName.toLowerCase()) {
            case FIELD_NAME:
                return repository.existsByName(value.toString());

            case FIELD_RE:
                return repository.existsByRe((Integer) value);
                
            default:
                throw new UnsupportedOperationException("Field name not supported");
        }
    }
}

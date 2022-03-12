package com.rmaciel.academy.core.validations.constraints.unique.services;

public interface FieldValueExists {
    boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException;
}

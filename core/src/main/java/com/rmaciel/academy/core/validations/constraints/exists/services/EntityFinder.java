package com.rmaciel.academy.core.validations.constraints.exists.services;

public interface EntityFinder {
    boolean existsBy(Object value, String fieldName) throws UnsupportedOperationException;
}

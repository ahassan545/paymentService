package com.payments.dataSource.validators;

public interface Validator<T> {

    Boolean isValid(T value);
}

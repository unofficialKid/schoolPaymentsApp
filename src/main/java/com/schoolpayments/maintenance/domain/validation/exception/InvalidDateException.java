package com.schoolpayments.maintenance.domain.validation.exception;

public class InvalidDateException extends RuntimeException {

    public InvalidDateException(String message) {
        super(message);
    }
}
package com.schoolpayments.maintenance.domain.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.schoolpayments.maintenance.domain.validation.exception.BusinessException;
import com.schoolpayments.maintenance.domain.validation.exception.InvalidDateException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BusinessException.class, InvalidDateException.class})
    public ResponseEntity<String> handleBusinessException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
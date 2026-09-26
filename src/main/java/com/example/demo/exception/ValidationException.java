package com.example.demo.exception;

import com.example.demo.dto.error.FieldErrorResponse;

import java.util.List;

public class ValidationException extends RuntimeException {
    private final List<FieldErrorResponse> errors;

    public ValidationException(List<FieldErrorResponse> errors) {
        super("Validation failed");
        this.errors = errors;
    }

    public List<FieldErrorResponse> getErrors() {
        return errors;
    }
}

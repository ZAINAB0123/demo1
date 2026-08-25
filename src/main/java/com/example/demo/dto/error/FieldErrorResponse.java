package com.example.demo.dto.error;

public record FieldErrorResponse (
        String field,
        String message
){
}

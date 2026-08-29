package com.example.demo.exception;

import com.example.demo.dto.error.ErrorResponse;
import com.example.demo.dto.error.FieldErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PageSizeException.class)
    public ResponseEntity<ErrorResponse> pageSizeException(
            PageSizeException e,
            HttpServletRequest request
    ) {
        log.warn(" Page size is greater than 100 {}", e.getMessage());
        return buildError(
                "PAGE_SIZE_TOO_LARGE",
                e.getMessage(),
                List.of(),
                HttpStatus.BAD_REQUEST,
                request);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> customerNotFoundException(
            CustomerNotFoundException e,
            HttpServletRequest request
    ) {
        log.warn(" Customer not found {}", e.getMessage());
        return buildError(
                "CUSTOMER_NOT_FOUND",
                e.getMessage(),
                List.of(),
                HttpStatus.NOT_FOUND,
                request
        );
    }

    @ExceptionHandler(DealNotFoundException.class)
    public ResponseEntity<ErrorResponse> dealNotFoundException(
            DealNotFoundException e,
            HttpServletRequest request
    ) {
        log.warn(" Deal not found {}", e.getMessage());
        return buildError(
                "DEAL_NOT_FOUND",
                e.getMessage(),
                List.of(),
                HttpStatus.NOT_FOUND,
                request
        );
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> taskNotFoundException(
            TaskNotFoundException e,
            HttpServletRequest request
    ) {
        log.warn(" Task not found {}", e.getMessage());
        return buildError(
                "TASK_NOT_FOUND",
                e.getMessage(),
                List.of(),
                HttpStatus.NOT_FOUND,
                request
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> dataIntegrityViolationException(
            DataIntegrityViolationException e,
            HttpServletRequest request
    ) {
        log.warn(" Validation failed for request: {}", request.getRequestURI());
        return buildError(
                "DATA_INTEGRITY_VIOLATION",
                "Database constraint violation",
                List.of(),
                HttpStatus.CONFLICT,
                request
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(
            MethodArgumentNotValidException e,
            HttpServletRequest request
    ) {
        log.warn(" Validation failed {}", e.getMessage());
        return buildError(
                "VALIDATION_ERROR",
                "Validation failed",
                e.getBindingResult().getFieldErrors()
                        .stream()
                        .map(fieldError -> new FieldErrorResponse(
                                fieldError.getField(),
                                fieldError.getDefaultMessage()
                        ))
                        .toList(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    private ResponseEntity<ErrorResponse> buildError(
            String code,
            String message,
            List<FieldErrorResponse> errors,
            HttpStatus status,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                code,
                message,
                request.getRequestURI(),
                Instant.now(),
                errors
        );

        return ResponseEntity.status(status).body(errorResponse);
    }
}

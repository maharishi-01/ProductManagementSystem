package com.assessment.ProductManagementSystem.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductManagementException.class)
    public ResponseEntity<String> handleNotFound(ProductManagementException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        StringBuilder errorMessage = new StringBuilder("Validation failed: ");
        result.getFieldErrors().forEach(error -> {
            errorMessage.append(error.getField()).append(" ").append(error.getDefaultMessage()).append(", ");
        });

        if (errorMessage.length() > 0) {
            errorMessage.setLength(errorMessage.length() - 2);
        }
        return ResponseEntity.badRequest().body(errorMessage.toString());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAll(Exception ex) {
        return ResponseEntity.status(500).body("Internal Server Error"+ ex.getMessage());
    }
}


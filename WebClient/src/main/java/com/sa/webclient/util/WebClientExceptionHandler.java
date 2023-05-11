package com.sa.webclient.util;

import com.sa.webclient.model.ApiError;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;


@ControllerAdvice
public class WebClientExceptionHandler {

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<String> handleWebClientResponseException(WebClientResponseException ex) {
        
        if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        }
      
        if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.status(ex.getStatusCode())
                .body("Ocurrió un error.");
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), errors);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);  
    }
    
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequestException(BadRequestException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }
}
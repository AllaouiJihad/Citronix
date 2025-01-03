package com.example.citronix.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(FarmSurfaceException.class)
    public ResponseEntity<String> handleFarmSurfacePositiveException(FarmSurfaceException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FieldEmptyException.class)
    public ResponseEntity<String> handleFarmSurfacePositiveException(FieldEmptyException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(FieldsSurbiggerThanFarmException.class)
    public ResponseEntity<String> handleFieldsSurbiggerThanFarmException(Exception ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(Exception ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TreeException.class)
    public ResponseEntity<String> handleTreeException(Exception ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentialsException(Exception exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TreeNotFoundException.class)
    public ResponseEntity<String> handleTreeNotFoundException(Exception exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HarvestNotFoundException.class)
    public ResponseEntity<String> handleHarvestNotFoundException(Exception exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HarvestAlreadySoldException.class)
    public ResponseEntity<String> handleHarvestAlreadySoldException(Exception exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(SaleNotFoundException.class)
    public ResponseEntity<String> handleSaleNotFoundException(Exception exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
}

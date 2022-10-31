package com.parkingslot.main.Exception;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomException extends ResponseEntityExceptionHandler {

	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println("Inside handleMethodArgumentNotValid method");
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Validation Failed", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
//
//        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
//    }
//
    @ExceptionHandler(SQLException.class)
    protected ResponseEntity<Object> EmailNotValid(Exception ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Email Already Exists");

        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
    }
//
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> checkPassword(ConstraintViolationException ex) {
        List l = new ArrayList<String>();
        ex.getConstraintViolations().forEach((error) -> {
            String errorMessage = error.getMessageTemplate();
            l.add(errorMessage);
        });
        return new ResponseEntity(l, HttpStatus.BAD_REQUEST);
    }
//
//    @ExceptionHandler(IllegalStateException.class)
//    public ResponseEntity<Object> buildingNoUnique(IllegalStateException ex) {
//        Map<String, String> errors = new HashMap<>();
//        errors.put("message", "Building number should be unique");
//        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> buildingNoUnique(ResourceNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return new ResponseEntity(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> invalidCredentials() {
    	System.out.println("CustomException.invalidCredentials()");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Username or Password");
    }
//
//    @ExceptionHandler(InvalidDataException.class)
//    public ResponseEntity<Object> InvalidDataException(InvalidDataException ex) {
//        Map<String, String> errors = new HashMap<>();
//        errors.put("message", ex.getMessage());
//        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
//    }

}

package com.webiotsolutions.school.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice(basePackages = "com.webiotsolutions.school")
public class SchoolExceptionHandler  {

        @ExceptionHandler(RecordNotFoundException.class)
        @ResponseStatus(value = HttpStatus.NOT_FOUND)
        public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
            List<String> details = new ArrayList<>();
            details.add(ex.getLocalizedMessage());
            ErrorResponse error = new ErrorResponse("Record Not Found", details);
            return new ResponseEntity(error, HttpStatus.NOT_FOUND);
        }
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public final ResponseEntity<Object> handleTokenExpiredException(ExpiredJwtException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        System.out.println("ExpiredJwtException");
        ErrorResponse error = new ErrorResponse("Session Expired", details);
        return new ResponseEntity(error, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<Object> handleBadCredentialsException(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Username or Password Incorrect", details);
        return new ResponseEntity(error, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}

package com.example.rest.webserivces.restful_web_services.Exceptions;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;

// import javax.net.ssl.HttpsURLConnection;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.rest.webserivces.restful_web_services.SocialMedia_Api.UserNotFoundException;

@ControllerAdvice

public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllException(Exception e, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), e.getMessage(), request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception e, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), e.getMessage(), request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDate.now(),
                "Errors: " + ex.getErrorCount() + " - " + ex.getFieldError().getDefaultMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}

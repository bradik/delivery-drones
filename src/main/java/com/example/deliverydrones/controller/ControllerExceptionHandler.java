package com.example.deliverydrones.controller;

import com.example.deliverydrones.error.DroneException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({DroneException.class})
    public ResponseEntity<Object> handleAccessDeniedException(
            Exception ex, WebRequest request) {

        DroneException exception = (DroneException) ex;

        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(exception.toString());
    }
}

package com.example.supplychain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class RestExceptionHandler {
    
    @ExceptionHandler(value = NoSupplierFoundException.class)
    public ResponseEntity<?> HandlerNoSupplierFoundException(){
        ErrorMessage message = ErrorMessage.builder()
                                            .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                                            .httpStatus(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                            .errorMessage("No Supplier Found")
                                            .build();
        return new ResponseEntity<ErrorMessage>(message,HttpStatus.BAD_REQUEST);
    }
}

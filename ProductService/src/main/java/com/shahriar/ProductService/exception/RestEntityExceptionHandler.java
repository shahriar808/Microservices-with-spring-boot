package com.shahriar.ProductService.exception;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.HttpStatus;
import com.shahriar.ProductService.model.ErrorResponse;

@ControllerAdvice
public class RestEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductServiceCustomException.class)
    public ResponseEntity<ErrorResponse> handleProductServiceException(ProductServiceCustomException exception){
        return new ResponseEntity<>(new ErrorResponse().builder()
        .errorCode(exception.getErrorCode())
        .errorMessage(exception.getMessage())
        .build(),
        HttpStatus.NOT_FOUND);
    }
}
package com.finance.finance.common.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

import com.finance.finance.common.response.ApiErrorResponse;

@RestControllerAdvice
public class GlobalExeceptionHandler {
    
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorResponse> handleBusiness(BusinessException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundExecption.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(ResourceNotFoundExecption ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiErrorResponse> handleUnauthorized(UnauthorizedException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<ApiErrorResponse> handleGeneric(Exception ex) {

    //     ex.printStackTrace();

    //     return buildResponse("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    // }  
    
    private ResponseEntity<ApiErrorResponse> buildResponse(String message, HttpStatus status) {
        ApiErrorResponse response = new ApiErrorResponse(
            false,
            message,
            status.value(),
            LocalDateTime.now()
        );
        return new ResponseEntity<>(response, status);
    }
}

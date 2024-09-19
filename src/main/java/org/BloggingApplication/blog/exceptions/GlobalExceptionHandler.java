package org.BloggingApplication.blog.exceptions;


import org.BloggingApplication.blog.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
    {
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message , false);
        return new ResponseEntity<ApiResponse>(apiResponse , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String , String>> methodArgsNotfoundExceptionHandler(MethodArgumentNotValidException ex)
    {
        Map<String , String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error ->  {
                String fieldName = ((FieldError)error).getField();
                String message = error.getDefaultMessage();
                resp.put(fieldName , message );
        });

        return new ResponseEntity<Map<String,String>>(resp , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex)
    {
        String supportedMethods = String.join(", ", ex.getSupportedHttpMethods().toString());

        String message = "The method " + ex.getMethod() + " is not supported for this request. Supported methods are " + supportedMethods;
        return new ResponseEntity<>(message, HttpStatus.METHOD_NOT_ALLOWED);

    }


}

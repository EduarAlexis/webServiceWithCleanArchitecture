package com.nisium.api.exceptionhandling;

import com.nisium.api.model.response.ErrorResponse;
import com.nisium.model.exception.EmailExistsException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiRestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<Object> handleTechnicalException(EmailExistsException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        return handleExceptionInternal(ex, ErrorResponse.builder().error(ex.getMsg()).build(), headers,
                HttpStatus.BAD_REQUEST, request);
    }
}

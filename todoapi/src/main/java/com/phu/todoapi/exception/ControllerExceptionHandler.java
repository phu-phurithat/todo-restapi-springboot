package com.phu.todoapi.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    ProblemDetail errorDetail = null;

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex) {
        errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), ex.getMessage());
        errorDetail.setProperty("description", "Resource not found");
        return errorDetail;
    }
}

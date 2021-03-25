package com.microgis.util;

import com.microgis.controller.dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.security.SignatureException;

@RestControllerAdvice
public class ExceptionProcessor {

    private static final String BAD_REQUEST = "Bad request";

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ResponseStatusException.class)
    public BaseResponse<String> handleResponseStatusException(ResponseStatusException e) {
        return new BaseResponse<>(false, e.getStatus().toString(), e.getReason());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResponse<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new BaseResponse<>(false, BAD_REQUEST, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SignatureException.class)
    public BaseResponse<String> handleSignatureException(SignatureException e) {
        return new BaseResponse<>(false, BAD_REQUEST, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public BaseResponse<String> handleException(Exception e) {
        return new BaseResponse<>(false, "Internal server error", e.getMessage());
    }

}
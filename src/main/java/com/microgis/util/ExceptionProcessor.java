package com.microgis.util;

import com.microgis.controller.dto.BaseResponse;
import com.microgis.util.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.security.SignatureException;

@RestControllerAdvice
public class ExceptionProcessor {

    private static final String BAD_REQUEST = "Bad request";

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public BaseResponse<String> handle401() {
        return new BaseResponse<>(false, "UnauthorizedException", null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResponse<String> handle500() {
        return new BaseResponse<>(false, BAD_REQUEST, null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SignatureException.class)
    public BaseResponse<String> handleSignatureException(SignatureException e) {
        return new BaseResponse<>(false, BAD_REQUEST, e.getMessage());
    }

}
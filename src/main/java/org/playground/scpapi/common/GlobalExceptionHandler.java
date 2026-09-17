package org.playground.scpapi.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorDto> handleValidationException(MethodArgumentNotValidException exception) {
        var errorDtos = new ArrayList<ErrorDto>();

        exception.getBindingResult().getFieldErrors().forEach(e -> errorDtos.add(new ErrorDto(e.getDefaultMessage())));

        return errorDtos;
    }
}

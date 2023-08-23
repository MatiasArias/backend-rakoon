package com.rakoon.backend.exception.handler;


import com.rakoon.backend.exception.InvalidDatatypeException;
import com.rakoon.backend.exception.RequiredFieldException;
import com.rakoon.backend.message.ResponseMessage;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidDatatypeException.class)
    public ResponseEntity<ResponseMessage> handleInvalidDatatypeException(InvalidDatatypeException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseMessage.builder()
                        .message(exception.getMessage()).build());
    }
    @ExceptionHandler(RequiredFieldException.class)
    public ResponseEntity<ResponseMessage> handleRequiredFieldException(RequiredFieldException exception){
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ResponseMessage.builder()
                        .message(exception.getMessage()).build());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            org.springframework.http.converter.HttpMessageConversionException.class,
            jakarta.validation.ConstraintViolationException.class
    })
    @ResponseBody
    public ErrorMessage handleBadRequest(Exception exception){
        return new ErrorMessage(exception.getMessage());
    }
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({
            HttpClientErrorException.Forbidden.class
    })
    @ResponseBody
    public ErrorMessage handleForbidden(Exception exception){
        return new ErrorMessage(exception.getMessage());
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            jakarta.persistence.EntityNotFoundException.class
    })
    @ResponseBody
    public ErrorMessage handleEntityNotFound(Exception exception){
        return new ErrorMessage(exception.getMessage());
    }
}

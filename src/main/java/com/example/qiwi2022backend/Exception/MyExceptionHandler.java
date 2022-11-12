package com.example.qiwi2022backend.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {
    private ResponseEntity<Object> constructException(final String message, final HttpStatus httpStatus){
        Map<String,Object> body = new LinkedHashMap<>();
        body.put("Timestamp", LocalDateTime.now());
        body.put("Message",message);
        return new ResponseEntity<>(body,httpStatus);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {NotValidRequestException.class,
            NotValidRequestIdException.class, IncorrectJwtTokenException.class, IncorrectUserTokenException.class})
    public ResponseEntity<Object> handleNotValidRequestException(final Exception e){
        return constructException(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {UserNotFoundException.class,
    ShopNotFoundException.class, DealNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(final Exception e){
        return constructException(e.getMessage(),HttpStatus.NOT_FOUND);
    }
}

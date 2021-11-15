package com.dau.mine.Error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomException {
    HttpHeaders responseHeader = new HttpHeaders();

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String,String>> ServerErrorException(HttpMessageNotReadableException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Map<String,String> map = new HashMap<>();
        map.put("code",httpStatus.getReasonPhrase());
        map.put("massage","요청값이 적절하지 않음");

        return new ResponseEntity<>(map, responseHeader, httpStatus);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,String>> ServerErrorException(RuntimeException  e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Map<String,String> map = new HashMap<>();
        map.put("code",httpStatus.getReasonPhrase());
        map.put("massage",e.getMessage());

        return new ResponseEntity<>(map, responseHeader, httpStatus);
    }
}

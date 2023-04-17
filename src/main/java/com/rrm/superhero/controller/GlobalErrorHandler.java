package com.rrm.superhero.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.rrm.superhero.exception.SuperheroNotFoundException;
 
@ControllerAdvice
public class GlobalErrorHandler {
 
    @ResponseBody
    @ExceptionHandler(SuperheroNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(HttpServletRequest request,
            HttpServletResponse response, Exception ex) {
 
        return ex.getMessage();
    }
     
}
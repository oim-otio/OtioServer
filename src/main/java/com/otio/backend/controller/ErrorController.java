package com.otio.backend.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.otio.backend.exception.UserException;

@RestControllerAdvice
@RequestMapping("/error")
public class ErrorController {
    @GetMapping("/tokennotfound")
    public void tokenNotFound() {
        throw new UserException("Token is not found. Please log in.");
    }

    @GetMapping("/incorrecttoken")
    public void incorrectToken() {
        throw new UserException("Token is incorrect. Please log in.");
    }

    @GetMapping("/timeout")
    public void timeoutTriggered() {
        throw new UserException("Time is out for this session. Please log in again.");
    }

    @GetMapping("/invalidrequest")
    public void invalidRequest() {
        throw new UserException("Request is invalid.");
    }

    @ExceptionHandler(UserException.class)
    public PayloadOutput<String> userExceptionHandler(UserException e) {
        return new PayloadOutput<String>(LocalDateTime.now(), "ERROR", e.getMessage());
    }

}

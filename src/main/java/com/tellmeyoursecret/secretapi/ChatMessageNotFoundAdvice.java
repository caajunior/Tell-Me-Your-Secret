package com.tellmeyoursecret.secretapi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ChatMessageNotFoundAdvice {
    
    @ResponseBody
    @ExceptionHandler(ChatMessageNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String chatMessageNotFoundHandler(ChatMessageNotFound ex) {
        return ex.getMessage();
    }
}

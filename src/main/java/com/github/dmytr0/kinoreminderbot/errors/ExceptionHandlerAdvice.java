package com.github.dmytr0.kinoreminderbot.errors;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Log4j2
@ControllerAdvice
@Component("ServiceExceptionHandlerAdvice")
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleTransitionException(Exception e) {
        log.error("Common error {}", e.getMessage(), e);
        return "Common error " + e.getMessage();
    }

}

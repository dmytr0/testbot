package com.github.dmytr0.kinoreminderbot.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@RestController
@RequestMapping("api")
public class MonitoringController {

    @GetMapping("/monitoring")
    @ResponseStatus(HttpStatus.OK)
    public void monitor(HttpServletRequest request) {
        log.info("Monitoring request " + request.getRemoteAddr());
    }

}

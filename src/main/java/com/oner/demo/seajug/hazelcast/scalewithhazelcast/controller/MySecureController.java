package com.oner.demo.seajug.hazelcast.scalewithhazelcast.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

@RestController
@RequestMapping(value = "/secure")
public class MySecureController {

    static final String SALUTE = "user %s from session %s";

    @RequestMapping(method = RequestMethod.GET, path = "/salute")
    String salute() {
        Object name = SecurityContextHolder.getContext().getAuthentication().getName();
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        return String.format(SALUTE, name, sessionId);
    }
}

package com.aikyam.prm.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/hello")
    public String hello() {
        return "Heyy, from Dugs paradise!!!!!";
    }
}

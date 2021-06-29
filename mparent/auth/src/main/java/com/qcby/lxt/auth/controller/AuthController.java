package com.qcby.lxt.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("auth")
public class AuthController {

    @GetMapping("hello")
    public String hello(){
        return "hello world auth!";
    }
}

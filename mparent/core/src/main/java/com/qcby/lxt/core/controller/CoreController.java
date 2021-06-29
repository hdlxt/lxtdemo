package com.qcby.lxt.core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("core")
public class CoreController {

    @GetMapping("hello")
    public String hello(){
        return "hello world core!";
    }
}

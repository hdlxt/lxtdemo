package com.lxt.demo.oss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("qiniu")
public class OssTestController {


    @RequestMapping
    public String index(){
        return "index";
    }
}

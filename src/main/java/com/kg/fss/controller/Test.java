package com.kg.fss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class Test {
    @RequestMapping("/test")
    public String test(){
        return "baidu";
    }
}

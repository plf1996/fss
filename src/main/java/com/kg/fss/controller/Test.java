package com.kg.fss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class Test {
    @RequestMapping("/baidu")
    public String testbaidu(){
        return "baidu";
    }
    @RequestMapping("/cesium")
    public String testcesium(){
        return "cesium";
    }
    @RequestMapping("/hello")
    public String hello(){
        return "HelloWorld";
    }

    @RequestMapping("/google")
    public String testgoogle(){
        return "google";
    }

    @RequestMapping("/tiandi")
    public String testtiandi(){
        return "tiandi";
    }
}

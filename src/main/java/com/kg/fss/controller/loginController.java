package com.kg.fss.controller;

import com.kg.fss.entity.User;
import com.kg.fss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class loginController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/userlogin",method = RequestMethod.POST)
    public String userlogin(User user){
        int result = userService.login(user.getUserName(),user.getPwd());
        if(result==1) {
            return "tiandi";
        }
        return "login";
        }
    }


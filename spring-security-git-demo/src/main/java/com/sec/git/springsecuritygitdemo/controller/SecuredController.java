package com.sec.git.springsecuritygitdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuredController {

    @RequestMapping(value = "secureUrl", method = RequestMethod.GET)
    public String getSecurePage(){
        return "Hiiii, you are on secure page !!!";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getRootPage(){
        return "Hiiii, you are on root page !!!";
    }
}

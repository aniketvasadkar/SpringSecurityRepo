package com.sec.fb.springsecurityfbsso.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureEndpoints {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getRootPage(){
        return "You are on root page" ;
    }

    @RequestMapping(value = "/secureUrl", method = RequestMethod.GET)
    public String getSecurePage(){
        return "You are on secure page" ;
    }
}

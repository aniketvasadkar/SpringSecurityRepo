package com.sec.demo.springsecuritysample.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SecureEndpoints {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getRootResource(Principal principal){
        return "Hi "+principal.getName()+", you are accessing root resource !!!";
    }

    @RequestMapping(value = "/secure", method = RequestMethod.GET)
    public String getSecureResource(Principal principal){
        return "Hi "+principal.getName()+", you are accessing secure resource !!!";
    }
}

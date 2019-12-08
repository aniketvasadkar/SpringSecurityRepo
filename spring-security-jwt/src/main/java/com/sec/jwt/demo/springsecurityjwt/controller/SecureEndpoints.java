package com.sec.jwt.demo.springsecurityjwt.controller;

import com.sec.jwt.demo.springsecurityjwt.model.JwtRequest;
import com.sec.jwt.demo.springsecurityjwt.model.JwtResponse;
import com.sec.jwt.demo.springsecurityjwt.service.CustomUserDetailsService;
import com.sec.jwt.demo.springsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SecureEndpoints {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getRootResource(Principal principal){
        return "Hi "+principal.getName()+", you are accessing root resource !!!";
    }

    @RequestMapping(value = "/secure", method = RequestMethod.GET)
    public String getSecureResource(Principal principal){
        return "Hi "+principal.getName()+", you are accessing secure resource !!!";
    }

    @RequestMapping(value = "authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> getToken(@RequestBody JwtRequest jwtRequest){

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        }catch(BadCredentialsException e){
            System.out.println("Username password mismatch");
        }

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());

        return ResponseEntity.ok(jwtUtil.createToken(userDetails.getUsername()));
    }
}

package com.example.spring03.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
    
    @GetMapping("/login")
    public String loginPage() {
        log.info("login()");
        
        return "/member/login";
        
    }
    
    @GetMapping("/register")
    public String registerPage() {
        log.info("register()");
        
        return "/member/register";
        
    }
    
    @GetMapping("/forgotPassword")
    public String forgotPassword() {
        log.info("forgotPassword()");
        
        return "/member/forgotPassword";
        
    }

}

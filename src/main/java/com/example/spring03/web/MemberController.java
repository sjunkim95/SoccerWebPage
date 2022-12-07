package com.example.spring03.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring03.dto.MemberRegisterDto;
import com.example.spring03.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {
    
    private final MemberService memberService;
    
    @GetMapping("/login")
    public String loginPage() {
        log.info("login()");
        
        return "/member/login";
        
    }
    
    @GetMapping("/register")
    public String register() {
        log.info("register() GET");
        
        return "/member/register";
        
    }
    
    @PostMapping("/register")
    public String register(MemberRegisterDto dto) {
        log.info("register(dto={}) POST", dto);
        
        memberService.registerMember(dto);
        
        return "redirect:login"; // 회원 가입 성공 후 이동
        
        
    }
    
    
    
    
    @GetMapping("/forgotPassword")
    public String forgotPassword() {
        log.info("forgotPassword()");
        
        return "/member/forgotPassword";
        
    }

}

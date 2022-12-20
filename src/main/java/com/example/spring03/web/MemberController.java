package com.example.spring03.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.spring03.dto.MailDto;
import com.example.spring03.dto.MemberRegisterDto;
import com.example.spring03.service.MailService;
import com.example.spring03.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {
    
    private final MemberService memberService;
    private final MailService mailService;
    
    @GetMapping("/login")
    public String loginPage() {
        log.info("login()");
        
        return "/member/login";
        
    }
    
    @GetMapping("/register")
    public void registerPage() {
        log.info("register() GET");
        
        return  ;
    }
    
    

    @PostMapping("/register")
    public String register(MemberRegisterDto dto, MailDto mdto) {
        log.info("signUp(dto={}, mdto = {})",dto, mdto);
        
        memberService.registerMember(dto);
        
        mdto.setTitle("축사 회원가입을 축하합니다.");
        mdto.setMessage(dto.getUsername() + "님의 회원가입을 축하드립니다.");
        mailService.justSend(mdto);
        
        return "redirect:/member/login";
    }
    
    @GetMapping("/checkid")
    @ResponseBody
    public ResponseEntity<String> checkUsername(String username) {
        log.info("checkUsername(username={})", username);
        
        String result = memberService.checkUsername(username);
        
        return ResponseEntity.ok(result);
    }
    
    
    @GetMapping("/forgotPassword")
    public String forgotPassword() {
        log.info("forgotPassword()");
        
        return "/member/forgotPassword";
        
    }

}
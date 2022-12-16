package com.example.spring03.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public void registerPage() {
        log.info("register() GET");
        
        return  ;
    }
    
    

    @PostMapping("/register")
    public String register(MemberRegisterDto dto) {
        log.info("signUp(dto={}) ",dto);
        
        memberService.registerMember(dto);
        
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
package com.example.spring03.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/view")
public class ViewController {
    
    @GetMapping
    public String main(){
        log.info("main()");

        return "main";
    }
}


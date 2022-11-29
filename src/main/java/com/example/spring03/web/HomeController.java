package com.example.spring03.web;
  
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping;
  
import lombok.extern.slf4j.Slf4j;
  
@Slf4j
@Controller // 스프링 컨트롤러 컴포넌트 
public class HomeController {
    @GetMapping("/") // 요청 URL/방식 매핑. 
    public String home(Model model) {
        log.info("home()");
        return "/view/main"; // View 이름 -> src/main/resources/templates/home.html 
        
    }
  
  }

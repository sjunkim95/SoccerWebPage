package com.example.spring03.web;
  
import java.io.IOException;
import java.util.Collection;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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

        final String soccerUrl = "https://www.donga.com/ISSUE/2022WorldCup";
        Connection conn = Jsoup.connect(soccerUrl);
        
        
        try {
            Document document = conn.get();
            Elements fixutreElements = document.select("div.tab_con02 > img");
            
            for (int j = 0; j < fixutreElements.size(); j++) {
                final String url = fixutreElements.get(j).attr("abs:src");
                System.out.println(url);

                
                model.addAttribute("newsFixture", url);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return "view/main"; // View 이름 -> src/main/resources/templates/home.html 
        
    }
    
    
  
  }
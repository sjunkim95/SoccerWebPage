package com.example.spring03.web;
  
import java.io.IOException;

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
        
        String URL = "https://sports.news.naver.com/wfootball/index";
        
        Document doc = null;
        try {
            doc = Jsoup.connect(URL).get();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        Elements elem = doc.select("div[class=\"home_news\"]");
        
        for (Element e : elem.select("span")) {
                System.out.println(e.text());
            } // Model 사용해서 보내자 
        
        return "view/main"; // View 이름 -> src/main/resources/templates/home.html 
        
    }
    
    
  
  }

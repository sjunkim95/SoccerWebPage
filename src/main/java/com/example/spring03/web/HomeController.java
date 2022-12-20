package com.example.spring03.web;

import java.util.List;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping;

import com.example.spring03.domain.SoccerPosts;
import com.example.spring03.service.SoccerPostsService;
  
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
  
@Slf4j
@RequiredArgsConstructor
@Controller // 스프링 컨트롤러 컴포넌트 
public class HomeController {
    
    private final SoccerPostsService soccerPostService;
    
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
            
            final String soccerUrl2 = "https://www.donga.com/news/Issue/051011";
            Connection conn2 = Jsoup.connect(soccerUrl2);
            Document document2 = conn2.get();
            
            Elements titleElements = document2.select("span.tit");
            
            for (int j = 0; j < titleElements.size(); j++) {
                final String url = titleElements.get(j).text();
                
                
                model.addAttribute("newsTitle1", titleElements.get(1));
                model.addAttribute("newsTitle2", titleElements.get(2));
                model.addAttribute("newsTitle3", titleElements.get(3));
                model.addAttribute("newsTitle4", titleElements.get(4));
                
            }
            
          Elements imgElements = document2.select("div.articleList > div.thumb > a > img");
          
          for (int j = 0; j < imgElements.size(); j++) {
             //   final String url = imgElements.get(j).attr("abs:src");
                model.addAttribute("newsImg1", imgElements.get(1).attr("abs:src"));
                model.addAttribute("newsImg2", imgElements.get(2).attr("abs:src"));
                model.addAttribute("newsImg3", imgElements.get(3).attr("abs:src"));
                model.addAttribute("newsImg4", imgElements.get(4).attr("abs:src"));
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return "view/main"; // View 이름 -> src/main/resources/templates/home.html 
        
    }
    
    
  
  }
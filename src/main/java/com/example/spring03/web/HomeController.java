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
                model.addAttribute("newsImg1", imgElements.get(0).attr("abs:src"));
                model.addAttribute("newsImg2", imgElements.get(1).attr("abs:src"));
                model.addAttribute("newsImg3", imgElements.get(2).attr("abs:src"));
                model.addAttribute("newsImg4", imgElements.get(3).attr("abs:src"));
            }
          
          final String plTableUrl = "https://www.goal.com/kr/%ED%94%84%EB%A6%AC%EB%AF%B8%EC%96%B4%EB%A6%AC%EA%B7%B8/%EC%88%9C%EC%9C%84/2kwbbcootiqqgmrzs6o5inle5";
          Connection conn3 = Jsoup.connect(plTableUrl);
          Document document3 = conn3.get();
          
          Elements plTableElements = document3.select("span.widget-match-standings__team--full-name");
          
          for (int j = 0; j < 10; j++) {
                final String team = plTableElements.get(j).text();
                
                model.addAttribute("plLeagueTable0", plTableElements.get(0));
                model.addAttribute("plLeagueTable1", plTableElements.get(1));
                model.addAttribute("plLeagueTable2", plTableElements.get(2));
                model.addAttribute("plLeagueTable3", plTableElements.get(3));
                model.addAttribute("plLeagueTable4", plTableElements.get(4));
                model.addAttribute("plLeagueTable5", plTableElements.get(5));
                model.addAttribute("plLeagueTable6", plTableElements.get(6));
                model.addAttribute("plLeagueTable7", plTableElements.get(7));
          }
          
          final String plFixture = "https://www.goal.com/kr/%ED%94%84%EB%A6%AC%EB%AF%B8%EC%96%B4%EB%A6%AC%EA%B7%B8/%EC%9D%BC%EC%A0%95-%EA%B2%B0%EA%B3%BC/2kwbbcootiqqgmrzs6o5inle5";
          Connection conn4 = Jsoup.connect(plFixture);

          
          Document document4 = conn4.get();
               
          Elements plLiveFixture = document4.select("span.team-name");
              
          for (int j = 0; j < plLiveFixture.size(); j++) { 
              final String plGame = plLiveFixture.get(j).text();
              model.addAttribute("plLeagueFixture0", plLiveFixture.get(0));
              model.addAttribute("plLeagueFixture1", plLiveFixture.get(1));
              model.addAttribute("plLeagueFixture2", plLiveFixture.get(2));
              model.addAttribute("plLeagueFixture3", plLiveFixture.get(3));
          }
          
          Elements fixtureDate = document4.select("div.nav-switch__label");

          for (int j = 0; j < fixtureDate.size(); j++) {
              final String fixDate = fixtureDate.get(j).text();

              model.addAttribute("plDate0", fixtureDate.get(0));
          } 
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return "view/main"; // View 이름 -> src/main/resources/templates/home.html 
        
    }
    
    
  
  }
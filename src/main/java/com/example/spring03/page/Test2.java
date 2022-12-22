package com.example.spring03.page;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Test2 {
    
    public static void main(String[] args) {
        
        final String soccerUrl = "https://www.goal.com/kr/%ED%94%84%EB%A6%AC%EB%AF%B8%EC%96%B4%EB%A6%AC%EA%B7%B8/2kwbbcootiqqgmrzs6o5inle5";
        Connection conn = Jsoup.connect(soccerUrl);

        try {
            Document document = conn.get();
             
            Elements rank = document.select("img");
            
            for (int j = 0; j < rank.size(); j++) {
                  final String url = rank.get(j).attr("src");
                  System.out.println(url);
            }
            
            Elements gamesPlayed = document.select("h3.title");
            
            for (int j = 0; j < gamesPlayed.size(); j++) {
                  final String url = gamesPlayed.get(j).text();
                  System.out.println(url);
            }
            

            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package com.example.spring03.page;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class FinalTest {
    
    public static void main(String[] args) {
        
        final String soccerUrl = "https://www.donga.com/news/Issue/051011";
        Connection conn = Jsoup.connect(soccerUrl);

        try {
            Document document = conn.get();
            
            Elements fixutreElements = document.select("div.tab_con02 > img");
            
//            for (int j = 0; j < fixutreElements.size(); j++) {
//                  final String url = fixutreElements.get(j).attr("abs:src");
//                  System.out.println(url);
//            }
            
//            Elements titleElements = document.select("span.tit");
//            
//            for (int j = 0; j < 5; j++) {
//                final String url = titleElements.get(1).text();
//                //final String url = linkElements.get(j).attr("abs:href");
//                //String tag = "<a href='" + url + "'><br>";
//                //System.out.println(tag);
//                System.out.println(url);
//                
//                
//            }
                
//            Elements urlElements = document.select("div.articleList > div.rightList > span.tit > a");
//
//            for (int j = 0; j < urlElements.size(); j++) {
//              //  final String url = linkElements.get(j).();
//                final String url = urlElements.get(j).attr("abs:href");
//                //String tag = "<a href='" + url + "'><br>";
//                //System.out.println(tag);
//                System.out.println(url);
//            }
//            
            Elements imgElements = document.select("div.articleList > div.thumb > a > img");
            
            for (int j = 0; j < imgElements.size(); j++) {
                  final String url = imgElements.get(j).attr("abs:src");
                  System.out.println(url);
              }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.example.spring03.page;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class FinalTest {
    
    public static void main(String[] args) {
        
        final String soccerUrl = "https://namu.wiki/w/2022%20FIFA%20%EC%9B%94%EB%93%9C%EC%BB%B5%20%EC%B9%B4%ED%83%80%EB%A5%B4/%EA%B8%B0%EB%A1%9D%EC%8B%A4";
        Connection conn = Jsoup.connect(soccerUrl);

        try {
            Document document = conn.ignoreHttpErrors(true).get();
             
            Elements imgElements = document.select("div.rDPkPNBD");
            
            for (int j = 0; j < imgElements.size(); j++) {
                  final String url = imgElements.get(j).text();
                  System.out.println(url);
              }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//for (int j = 0; j < fixutreElements.size(); j++) {
//final String url = fixutreElements.get(j).attr("abs:src");
//System.out.println(url);
//}

//Elements titleElements = document.select("span.tit");
//
//for (int j = 0; j < 5; j++) {
//final String url = titleElements.get(1).text();
////final String url = linkElements.get(j).attr("abs:href");
////String tag = "<a href='" + url + "'><br>";
////System.out.println(tag);
//System.out.println(url);
//
//
//}

//Elements urlElements = document.select("div.articleList > div.rightList > span.tit > a");
//
//for (int j = 0; j < urlElements.size(); j++) {
////  final String url = linkElements.get(j).();
//final String url = urlElements.get(j).attr("abs:href");
////String tag = "<a href='" + url + "'><br>";
////System.out.println(tag);
//System.out.println(url);
//}
//

package com.example.spring03.page;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class FinalTest {

    public static void main(String[] args) {

        final String soccerUrl = "https://www.topscorersfootball.com/premier-league";
        Connection conn = Jsoup.connect(soccerUrl);

        try {
            Document document = conn.get();
            
            Elements topScorer = document.select("td.text-center");

            for (int j = 0; j < topScorer.size(); j++) {
                final String url = topScorer.get(j).text();
                System.out.println(url);
            }
            
//            Elements articleTitle = document.select("div.match-status > time");
//
//            for (int j = 0; j < articleTitle.size(); j++) {
//                final String url = articleTitle.get(j).text();
//                System.out.println(url);
//            }
            
//            Elements articleTitle = document.select("div.team-home > span.crest > img");
//
//            for (int j = 0; j < articleTitle.size(); j++) {
//                System.out.println(articleTitle.get(j));
//                final String url = articleTitle.get(j).attr("src");
//                System.out.println(url);
//            }

         //   Elements topAssist = document.select("td[data-reactid='7951v5so4.1.0.1.1.0.0.1.$PO78830.1']");

       //     for (int j = 0; j < topAssist.size(); j++) {
        //        final String url = topAssist.get(j).text();
        //        System.out.println(url);
        //    }
            // 어시스트
//            final String soccerUrl1 = "https://www.bbc.com/sport/football/premier-league/top-scorers/assists";
//            Connection conn1 = Jsoup.connect(soccerUrl1);
//            Document document1 = conn1.get();
//
//            Elements assist = document1.select("span.gs-u-vh");
//
//            for (int j = 0; j < assist.size(); j++) {
//                final String url = assist.get(j).text();
//                System.out.println(url);
//            }

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

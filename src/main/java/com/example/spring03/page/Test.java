package com.example.spring03.page;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {

    public static void main(String[] args) {

        final String soccerUrl = "https://football.scoreman.com/cupmatch/2022/75";
                //"https://football.scoreman.com/league/36";
        
        Connection conn = Jsoup.connect(soccerUrl);

        try {
            Document document = conn.get();
            Elements titleElements = document.select("div.mcontent > div.data");

            for (int j = 0; j < titleElements.size(); j++) {
                final String url = titleElements.get(j).text();
  
                System.out.println(url);
            }

 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Elements linkElements = document.select("a.course_card_front");

// for (int j = 0; j < linkElements.size(); j++) {
// final String url = linkElements.get(j).attr("abs:href");
// }

// Elements elem = doc.getElementsByClass("home_news");

// for (Element e : elem.select("a href")) {
// System.out.println(e.text());
// }

//       for (Element item : elem.select("href")) {
//           String title = item.selectFirst("title").text();
//           String href = item.selectFirst("link").text();
//       }

//        try {
//            Document doc = Jsoup.connect("http://rss.donga.com/sportsdonga/soccer.xml").get();
//            Elements items = doc.select("item");
//            
//            File writeFile = new File("src/main/resources/templates/data/news.html");
//            FileOutputStream fos = new FileOutputStream(writeFile);
//            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
//            BufferedWriter bw = new BufferedWriter(osw);
//            
//            for (Element item : items) {
//                String title = item.selectFirst("title").text();
//                String href = item.selectFirst("link").text();
//                
//                String tag = "<a href='" + href + "'>" + title + "</a><br>";
//                bw.write(tag);
//                bw.newLine();
//                
//            }
//            
//            bw.flush();
//            bw.close();
//            System.out.println("성공");
//        } catch (IOException e) {
//            System.out.println("실패");
//            e.printStackTrace();
//        }

/// INFLEARN 예제
//final String inflearnUrl = "https://www.inflearn.com/courses/it-programming";
//Connection conn = Jsoup.connect(inflearnUrl);
//
//try {
//    Document document = conn.get();
//    Elements linkElements = document.select("a.course_card_front");
//
//    for (int j = 0; j < linkElements.size(); j++) {
//        final String url = linkElements.get(j).attr("abs:href");
//        System.out.println(url);
//        String tag = "<a href='" + url + "'><br>";
//        System.out.println(tag);
//    }

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
        
        final String soccerUrl = "https://www.donga.com/ISSUE/2022WorldCup";
        Connection conn = Jsoup.connect(soccerUrl);

        try {
            Document document = conn.get();
            Elements fixutreElements = document.select("div.tab_con02 > img");
            
            for (int j = 0; j < fixutreElements.size(); j++) {
                  final String url = fixutreElements.get(j).attr("abs:src");
                  System.out.println(url);
            }
         //   for (Element element : linkElements) {
        //        System.out.println(element.attr("abs:src"));
         //   }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

       //Elements linkElements = document.select("a.course_card_front");

      // for (int j = 0; j < linkElements.size(); j++) {
       //    final String url = linkElements.get(j).attr("abs:href");
       //}
       
       //Elements elem = doc.getElementsByClass("home_news");
       
     //  for (Element e : elem.select("a href")) {
       //        System.out.println(e.text());
      //     }
       
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
        
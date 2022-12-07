package com.example.spring03.page;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




public class Test {

    public static void main(String[] args) throws IOException {
        
       String URL = "https://sports.news.naver.com/wfootball/index";
       
       Document doc = null;
       doc = Jsoup.connect(URL).get();
       
       Elements elem = doc.select("div[class=\"home_news\"]");
       
       for (Element e : elem.select("span")) {
               System.out.println(e.text());
           }
           
       }
}      
        
        

        
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
        
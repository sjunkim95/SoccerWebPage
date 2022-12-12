package com.example.spring03.page;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
    import org.jsoup.select.Elements;

import java.io.IOException;

public class Test2 {

    public static void main(String[] args) {
        final String inflearnUrl = "https://www.inflearn.com/courses/it-programming";
        Connection conn = Jsoup.connect(inflearnUrl);

        try {
            Document document = conn.get();
            Elements linkElements = document.select("a.course_card_front");

            for (int j = 0; j < linkElements.size(); j++) {
                final String url = linkElements.get(j).attr("abs:href");
                System.out.println(url);
                String tag = "<a href='" + url + "'><br>";
                System.out.println(tag);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
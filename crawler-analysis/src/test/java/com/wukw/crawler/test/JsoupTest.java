package com.wukw.crawler.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;


public class JsoupTest {
    @Test
    public void test() {
        Element element = Jsoup.parse("<table><tr><td>user</td><td>cc</td></tr><tr><td>pass</td><td>123</td></tr></table>");
        Elements table = element.select("table tr");
            Elements a = table.select("td");
            System.out.println(a.text());



    }
}

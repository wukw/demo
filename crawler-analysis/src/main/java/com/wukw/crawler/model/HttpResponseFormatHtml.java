package com.wukw.crawler.model;

import lombok.AllArgsConstructor;
import org.jsoup.nodes.Document;

@AllArgsConstructor
public class HttpResponseFormatHtml extends HttpResponseFormatBody {
    Document document;

    @Override
    public String element(String element) {
        return document.body().select(element).first().text();
    }

    @Override
    public String attr(String attr) {
        return document.body().attr(attr);
    }
}

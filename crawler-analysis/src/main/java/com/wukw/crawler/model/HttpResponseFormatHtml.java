package com.wukw.crawler.model;

import lombok.AllArgsConstructor;
import org.jsoup.select.Elements;

@AllArgsConstructor
public class HttpResponseFormatHtml extends HttpResponseFormatBody {
    Elements documents;

    @Override
    public Elements element(String element) {
        return documents.select(element);
    }

    @Override
    public String attr(String attr) {
        return documents.attr(attr);
    }

    @Override
    public int size() {
        return documents.size();
    }

    @Override
    public HttpResponseFormatBody getIndex(int index) {
        return new HttpResponseFormatHtml(documents.eq(index));
    }

    @Override
    public String toString() {
        return documents.text();
    }


}

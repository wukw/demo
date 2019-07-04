package com.wukw.crawler.model;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class HttpResponseFormatJson extends HttpResponseFormatBody {
    Map json;
    @Override
    public String element(String element) {
        return null;
    }

    @Override
    public String attr(String attr) {
        return null;
    }
}

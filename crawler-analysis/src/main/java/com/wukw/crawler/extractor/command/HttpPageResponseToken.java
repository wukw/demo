package com.wukw.crawler.extractor.command;


import com.wukw.crawler.model.HttpResponse;
import com.wukw.crawler.model.config.HttpPageResponse;

import java.util.Map;

public class HttpPageResponseToken implements CommandToken<HttpResponse, Map<String, String>> {
    HttpPageResponse httpPageResponse;

    @Override
    public Map<String, String> doCommmand(HttpResponse httpPageResponse) {
        return null;
    }
}

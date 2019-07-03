package com.wukw.crawler.test;

import com.wukw.crawler.extractor.command.HttpPageRequestToken;
import com.wukw.crawler.model.config.HttpPageRequest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class OkHttpTest {
    @Test
    public void SendHttpTest() {
        Map<String, String> headers = new HashMap<>();
        headers.put("1", "1");
        HttpPageRequest httpPageRequest = new HttpPageRequest();
        httpPageRequest.setUrl("https://www.baidu.com/");
        httpPageRequest.setMediaType("application/x-www-form-urlencoded");
        httpPageRequest.setMethod("POST");
        httpPageRequest.setBody("1=1");
        httpPageRequest.setHeaders(headers);

        HttpPageRequestToken.httpPageRequestToken.doCommmand(httpPageRequest);

    }
}

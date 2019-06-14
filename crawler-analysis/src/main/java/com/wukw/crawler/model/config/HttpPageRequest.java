package com.wukw.crawler.model.config;

import java.util.HashMap;
import java.util.Map;

public class HttpPageRequest {
    String url;
    private Map<String, String> headers = new HashMap();
    private String body;
    private String charset;
    private Integer timeout;
}

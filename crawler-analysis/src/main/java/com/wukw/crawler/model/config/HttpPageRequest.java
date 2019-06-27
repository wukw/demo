package com.wukw.crawler.model.config;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class HttpPageRequest {
    String url;
    private String method;
    private Map<String, String> headers = new HashMap();
    private String body;
    private String charset;
    private Integer timeout;
}

package com.wukw.crawler.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class HttpResponse {
    private String body;
    private Map<String, List<String>> headers;
}

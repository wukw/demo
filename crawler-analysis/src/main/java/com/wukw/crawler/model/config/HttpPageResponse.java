package com.wukw.crawler.model.config;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class HttpPageResponse {
    private String body;
    private Map<String, List<String>> headers;
}

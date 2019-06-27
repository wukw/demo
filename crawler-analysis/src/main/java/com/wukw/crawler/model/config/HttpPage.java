package com.wukw.crawler.model.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class HttpPage {
    private String condition;
    private String loop;
    private String id;
    private Boolean needStore;
    private Boolean redirect = false;
    private Integer maxRedirects;
    private Boolean image = false;
    private HttpPageRequest request;
    private HttpPageResponse response;
    private String nextPageId;
    private Boolean refreshEnabled;
    private String refreshFromPageId;
}

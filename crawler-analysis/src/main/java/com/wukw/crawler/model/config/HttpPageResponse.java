package com.wukw.crawler.model.config;

import lombok.Data;

import java.util.List;

@Data
public class HttpPageResponse {
    List<HttpPageResponseExtractor> httpPageResponseExtractors;

}

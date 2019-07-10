package com.wukw.crawler.model.config;

import lombok.Data;

import java.util.List;

@Data
public class HttpPageResponseExtractor {
    String type;
    String element;
    HttpPageVar var;
    List<HttpPageResponseExtractorsStroe> stroes;
    HttpPageResponseExtractor httpPageResponseExtractors;
}

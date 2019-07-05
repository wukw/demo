package com.wukw.crawler.model.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
public class HttpPageResponseExtractor {
    String type;
    String element;
    String var;
    List<HttpPageResponseExtractorsStroe> stroes;
    HttpPageResponseExtractor httpPageResponseExtractors;
}

package com.wukw.crawler.model.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
public class HttpPageConfig {
    private List<HttpPage> httpPageList;
}

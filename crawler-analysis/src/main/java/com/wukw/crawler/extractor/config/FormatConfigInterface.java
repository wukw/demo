package com.wukw.crawler.extractor.config;

import com.wukw.crawler.model.config.HttpPageConfig;

/**
 * 配置文件转 HttpPageConfig
 */
public interface FormatConfigInterface {
    public HttpPageConfig getHttpPageConfig(String ConfigInfo);
}

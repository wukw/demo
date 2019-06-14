package com.wukw.crawler.extractor.config;

import com.wukw.crawler.model.config.HttpPageConfig;


public interface ConfigResource {


    public String getConfig(String ConfigPath);

    public HttpPageConfig getHttpPageConfig(String ConfigInfo);


}

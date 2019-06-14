package com.wukw.crawler.extractor;

import com.wukw.crawler.extractor.config.BaseConfigResource;
import com.wukw.crawler.model.config.HttpPage;
import com.wukw.crawler.model.config.HttpPageConfig;

public class Extractor {
    private BaseConfigResource baseConfigResource;

    public void exe(String configPath) {
        String configInfo = baseConfigResource.getConfig(configPath);
        HttpPageConfig httpPageConfig = baseConfigResource.getHttpPageConfig(configInfo);
    }

    public void exeHttpPages(HttpPageConfig httpPageConfig) {

        for (HttpPage page : httpPageConfig.getHttpPageList()) {

        }
    }

}

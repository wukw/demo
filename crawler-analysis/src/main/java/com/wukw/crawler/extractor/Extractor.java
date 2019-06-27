package com.wukw.crawler.extractor;

import com.wukw.crawler.extractor.command.ConditionCommandToken;
import com.wukw.crawler.extractor.command.HttpRequestCommandToken;
import com.wukw.crawler.extractor.config.BaseConfigResource;
import com.wukw.crawler.model.config.HttpPage;
import com.wukw.crawler.model.config.HttpPageConfig;
import com.wukw.crawler.model.config.HttpPageRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Extractor {
    private BaseConfigResource baseConfigResource;
    //条件指令
    private ConditionCommandToken conditionCommandToken;
    //HttpRequest请求指令
    private HttpRequestCommandToken httpRequestCommandToken;

    public void exe(String configPath) {
        String configInfo = baseConfigResource.getConfig(configPath);
        HttpPageConfig httpPageConfig = baseConfigResource.getHttpPageConfig(configInfo);
    }

    public void exeHttpPages(HttpPageConfig httpPageConfig) {

        for (HttpPage page : httpPageConfig.getHttpPageList()) {
            if (conditionCommandToken.doCommmand(page.getCondition())) {
                httpRequestCommandToken.doCommmand(page.getRequest());

            } else {
                log.info("条件为满足:{}无需执行", page.getCondition());
            }

        }
    }

}

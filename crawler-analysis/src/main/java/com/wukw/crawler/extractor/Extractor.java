package com.wukw.crawler.extractor;

import com.wukw.crawler.extractor.command.ConditionToken;
import com.wukw.crawler.extractor.command.HttpPageRequestToken;
import com.wukw.crawler.extractor.config.DefaultConfigResource;
import com.wukw.crawler.extractor.config.FormatConfigInterface;
import com.wukw.crawler.model.config.HttpPage;
import com.wukw.crawler.model.config.HttpPageConfig;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Extractor {
    private DefaultConfigResource configResource;
    private FormatConfigInterface formatConfigInterface;
    //条件指令
    private ConditionToken conditionCommandToken;
    //HttpRequest请求指令
    private HttpPageRequestToken httpRequestCommandToken;

    public void exe(String configPath) {
        String configInfo = configResource.getConfig(configPath);
        HttpPageConfig httpPageConfig = formatConfigInterface.getHttpPageConfig(configInfo);
    }

    public void exeHttpPages(HttpPageConfig httpPageConfig) {

        for (HttpPage page : httpPageConfig.getHttpPageList()) {
            if (ConditionToken.conditionToken.doCommmand(page.getCondition())) {
                HttpPageRequestToken.httpPageRequestToken.doCommmand(page.getRequest());
            } else {
                log.info("条件为满足:{}无需执行", page.getCondition());
            }

        }
    }

}

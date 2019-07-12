package com.wukw.crawler.extractor.config;

import com.thoughtworks.xstream.XStream;
import com.wukw.crawler.model.config.HttpPageConfig;


public class XmlConfig implements FormatConfigInterface {
    @Override
    public HttpPageConfig getHttpPageConfig(String ConfigInfo) {
        return (HttpPageConfig) new XStream().fromXML(ConfigInfo);
    }
}

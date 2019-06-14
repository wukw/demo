package com.wukw.crawler.extractor.config;

import com.wukw.crawler.utils.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public abstract class BaseConfigResource implements ConfigResource {

    /**
     * 默认实现 从resources 获取文件
     *
     * @param ConfigPath
     * @return
     */
    @Override
    public String getConfig(String ConfigPath) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        try {
            InputStream inputStream = resourceLoader.getResource(ConfigPath).getInputStream();
            String configInfo = IOUtils.inputToString(inputStream);
            return configInfo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}

package com.wukw.crawler;

import com.wukw.crawler.extractor.Extractor;
import com.wukw.crawler.extractor.config.ConfigResource;
import com.wukw.crawler.extractor.config.DefaultConfigResource;
import com.wukw.crawler.extractor.config.FormatConfigInterface;
import com.wukw.crawler.extractor.config.XmlConfig;
import com.wukw.crawler.task.TaskParam;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Data
public class FrameWork {

    public static void main(String[] args) {

    }

    private FormatConfigInterface formatConfigInterface;
    private ConfigResource configResource;
    private ThreadPoolExecutor executor;
    private Extractor extractor;


    public class FrameWorkBuilder {
        FrameWork frameWork = null;

        public FrameWorkBuilder builder() {
            frameWork = new FrameWork();
            return new FrameWorkBuilder();
        }

        public FrameWorkBuilder formatConfigInterface(FormatConfigInterface formatConfigInterface) {
            frameWork.setFormatConfigInterface(formatConfigInterface);
            return this;
        }

        public FrameWorkBuilder formatConfigInterface(ConfigResource configResource) {
            frameWork.setConfigResource(configResource);
            return this;
        }

        public FrameWorkBuilder executor(ThreadPoolExecutor executor) {
            frameWork.setExecutor(executor);
            return this;
        }

        public FrameWorkBuilder executor(Extractor extractor) {
            frameWork.setExtractor(extractor);
            return this;
        }

        public FrameWork build(Integer queueSize) {
            if (frameWork.getExtractor() == null) {
                throw new NullPointerException("线程池不能为空");
            }
            if (frameWork.getFormatConfigInterface() == null) {
                extractor.setFormatConfigInterface(new XmlConfig());
            }
            if (frameWork.getConfigResource() == null) {
                extractor.setConfigResource(new DefaultConfigResource());
            }

            return null;
        }

    }


    /**
     * 添加任务接口
     */
    public void execTask(TaskParam taskParam) {
        taskParam.setFrameWork(this);
        executor.execute(taskParam);

    }

}

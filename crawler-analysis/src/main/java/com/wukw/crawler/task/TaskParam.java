package com.wukw.crawler.task;

import com.wukw.crawler.FrameWork;
import lombok.Data;

@Data
public class TaskParam implements Runnable {
    public FrameWork frameWork;
    public String configName;

    @Override
    public void run() {
        frameWork.getExtractor().exe(configName);


    }
}

package com.wukw.crawler.model;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestModel {
    String a;

    public String getA() {
        log.info("调用getA方法");
        return a;
    }

    public void setA(String a) {
        log.info("调用setA方法{}",a);
        this.a = a;
    }
}

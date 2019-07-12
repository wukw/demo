package com.wukw.crawler.model.config;

public class HttpPageVar {
    String clazz;
    String alias;

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getAlias() {
        return alias == null?"":alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}

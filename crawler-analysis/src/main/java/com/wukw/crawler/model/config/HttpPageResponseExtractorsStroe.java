package com.wukw.crawler.model.config;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpPageResponseExtractorsStroe implements Cloneable {
    String attr;
    String element;
    String objectName;
    String objectAlias;
    /**
     * -1 表示 不设置 取最后一个
     */
    Integer index;
    String objectField;

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectAlias() {
        return objectAlias == null ? "" : objectAlias;
    }

    public void setObjectAlias(String objectAlias) {
        this.objectAlias = objectAlias;
    }

    public Integer getIndex() {
        return index == null ? 0 : index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getObjectField() {
        return objectField;
    }

    public void setObjectField(String objectField) {
        this.objectField = objectField;
    }

    public HttpPageResponseExtractorsStroe clone() {
        try {
            return (HttpPageResponseExtractorsStroe) super.clone();
        } catch (CloneNotSupportedException e) {
            log.error("store克隆出错{}", e.getMessage());
        }
        return null;
    }
}

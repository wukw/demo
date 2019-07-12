package com.wukw.crawler.extractor.command;

import com.wukw.crawler.extractor.heap.HeapUtils;
import com.wukw.crawler.model.config.HttpPageResponseExtractorsStroe;
import com.wukw.crawler.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Stack;

@AllArgsConstructor
@Slf4j
public class StoreToken implements CommandToken<Stack<Object>, Object> {
    HttpPageResponseExtractorsStroe httpPageResponseExtractorsStroe;

    @Override
    public Object doCommmand(Stack<Object> stack) {
        ElementToken token = new ElementToken(httpPageResponseExtractorsStroe.getElement());

        Object element = null;
        if (httpPageResponseExtractorsStroe.getElement() != null) {
            element = token.doCommmand(stack);
        }
        if (httpPageResponseExtractorsStroe.getAttr() != null) {
            element = token.attr(stack);
        }
        String objectName = httpPageResponseExtractorsStroe.getObjectName();
        String objectField = httpPageResponseExtractorsStroe.getObjectField();
        Object obj = HeapUtils.getObj(objectName, httpPageResponseExtractorsStroe.getObjectAlias(), httpPageResponseExtractorsStroe.getIndex());
        if (obj == null) {
            return null;
        }
        Method method = null;
        try {
            //首字母大写 调用set方法
            method = obj.getClass().getMethod("set" + StringUtils.captureName(objectField), String.class);
        } catch (NoSuchMethodException e) {
            log.info("class:{}无该属性{}get|set方法", objectName, objectField);
            return null;
        }
        try {
            method.invoke(obj, element.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return element;

    }
}

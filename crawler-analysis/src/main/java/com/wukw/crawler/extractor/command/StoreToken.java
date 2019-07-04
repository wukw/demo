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
public class StoreToken implements CommandToken<Stack<Object>, String> {
    HttpPageResponseExtractorsStroe httpPageResponseExtractorsStroe;

    @Override
    public String doCommmand(Stack<Object> stack) {
        String value = new ElementToken(httpPageResponseExtractorsStroe.getElement()).doCommmand(stack);
        String objectName = httpPageResponseExtractorsStroe.getObjectName();
        String objectField = httpPageResponseExtractorsStroe.getObjectField();
        Object obj = HeapUtils.get(objectName);
        if (obj == null) {
            return null;
        }
        Method method = null;
        try {
            //首字母大写 调用set方法
            method = obj.getClass().getMethod("set" + StringUtils.captureName(objectField), String.class);
        } catch (NoSuchMethodException e) {
            log.info("class:{}无该属性{}get方法", objectName, objectField);
            return null;
        }
        try {
            method.invoke(obj, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return value;

    }
}

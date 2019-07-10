package com.wukw.crawler.extractor.command;

import com.wukw.crawler.extractor.heap.HeapUtils;
import com.wukw.crawler.model.config.HttpPageVar;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class VarToken implements CommandToken<String, Object> {
    HttpPageVar httpPageVar;

    @Override
    public Object doCommmand(String index) {
        Class clazz = null;
        Object obj = null;
        try {
            clazz = Class.forName(httpPageVar.getClazz());
            obj = clazz.newInstance();
            HeapUtils.saveObj(httpPageVar.getBeanName() + index, obj);
        } catch (ClassNotFoundException e) {
            log.error("类:{}无法被找到", httpPageVar.getClazz());
        } catch (IllegalAccessException e) {
            log.error("类:{}实例化失败", httpPageVar.getClazz());
        } catch (InstantiationException e) {
            log.error("类:{}实例化失败", httpPageVar.getClazz());
        }
        return obj;
    }
}

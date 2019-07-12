package com.wukw.crawler.extractor.heap;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Map;

@Slf4j
public class HeapUtils {
    public static final String taskId = "taskId";

    static ContextHeap contextHeap = new ContextHeap();
    static PageHeap pageHeap = new PageHeap();
    static ThreadLocal<Map<String, Object>> params = new ThreadLocal();


    /**
     * @param name #{name} --> PageHeap
     *             ${name} --> ContextHeap
     * @return
     */
    public static Object get(String name) {
        if (name.contains("$")) {
            return contextHeap.get(name);
        }
        if (name.contains("#")) {
            return pageHeap.get(name);
        }
        return name;
    }

    public static Object put(String k, Object v) {
        if (k.contains("$")) {
            return contextHeap.put(k, v);
        }
        if (k.contains("#")) {
            return pageHeap.put(k, v);
        }
        return false;
    }

    /**
     * 会将text 中所有 ${name} || #{name} 替换成 相对应的的值
     *
     * @param text
     * @return
     */
    public static String replace(String text) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        text = pageHeap.replace(text);
        text = contextHeap.replace(text);
        return text;
    }

    public static Object getThread(String name) {
        return params.get().get(name);
    }

    public static Object removeThread(String name) {
        return params.get().remove(name);
    }

    public static Object setThread(String name, String value) {
        return params.get().put(name, value);
    }

    public static void saveObj(String name, String aliasName, Object obj, int limit) {
        log.debug("保存对象:{}别名:{}limit:{}", name, aliasName, limit);
        ObjectHeap.getInstance().addObject(name, aliasName, obj, limit);
    }

    public static Object getObj(String name, String aliasName, int index) {
        name = replace(name);
        log.debug("获取对象:{}别名:{}limit:{}", name, aliasName, index);
        return ObjectHeap.getInstance().getObject(name, aliasName, index);
    }

    public static Integer getObjCount(String name, String alias) {
        return ObjectHeap.getInstance().getObjCount(name, alias);
    }

    public static void init() {
        contextHeap.threadInit();
        pageHeap.threadInit();
        ObjectHeap.getInstance().threadInit();
        //实体类路径
        //params.get().put("modelPath", System.getenv("modelPath"));


    }

    public static void destroy() {
        contextHeap.threadDestroy();
        pageHeap.threadDestroy();
        ObjectHeap.getInstance().threadDestroy();
    }
}

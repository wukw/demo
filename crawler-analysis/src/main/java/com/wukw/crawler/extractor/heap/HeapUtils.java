package com.wukw.crawler.extractor.heap;


import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HeapUtils {
    public static final String taskId = "taskId";

    static ContextHeap contextHeap = new ContextHeap();
    static PageHeap pageHeap = new PageHeap();
    static ThreadLocal<Map<String, Object>> params = new ThreadLocal<>();
    static ThreadLocal<ConcurrentHashMap> objectMap = new ThreadLocal<ConcurrentHashMap>();


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

    public static void saveObj(String name, Object obj) {
        objectMap.get().put(name, obj);
    }

    public static Object getObj(String name) {
        name = replace(name);
        return objectMap.get().get(name);
    }

    public static void init() {
        contextHeap.init();
        pageHeap.init();
        objectMap.set(new ConcurrentHashMap());


    }

    public static void destroy() {
        contextHeap.destroy();
        pageHeap.destroy();
        objectMap.remove();
    }
}

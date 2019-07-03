package com.wukw.crawler.extractor.heap;


import org.springframework.util.StringUtils;

import java.util.Map;

public class HeapUtils {
    static ContextHeap contextHeap = new ContextHeap();
    static PageHeap pageHeap = new PageHeap();
    static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();


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
        return null;
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
        return threadLocal.get().get(name);
    }

    public static Object removeThread(String name) {
        return threadLocal.get().remove(name);
    }

    public static Object setThread(String name, String value) {
        return threadLocal.get().put(name, value);
    }
}

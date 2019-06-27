package com.wukw.crawler.extractor.heap;


public class HeapUtils {
    static ContextHeap contextHeap = new ContextHeap();
    static PageHeap pageHeap = new PageHeap();


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
        text = pageHeap.replace(text);
        text = contextHeap.replace(text);
        return text;
    }
}

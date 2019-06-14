package com.wukw.crawler.extractor.heap;



public class HeapUtils {

    /**
     *
     * @param name   #[name] --> PageHeap $[name] --> ContextHeap
     * @return
     */
    public static Object get(String name) {
        if (name.contains("$")) {
            return ContextHeap.get(name, null);
        }
        if (name.contains("#")) {
            return PageHeap.get(name, null);
        }
        return name;

    }
}

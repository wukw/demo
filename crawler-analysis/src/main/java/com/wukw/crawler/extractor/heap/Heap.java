package com.wukw.crawler.extractor.heap;

public interface Heap {

    public static boolean put(String k, Object v) {
        return false;
    }

    public static Object get(String k, String defaultV) {
        return null;
    }

    public static boolean clean(String key) {
        return false;
    }

}

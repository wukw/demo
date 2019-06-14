package com.wukw.crawler.extractor.heap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class BaseHeap {
    static ConcurrentHashMap<String, Object> heap;
    static Pattern pattern;

    public BaseHeap(ConcurrentHashMap concurrentHashMap, Pattern p) {
        heap = concurrentHashMap;
        pattern = p;
    }

    public static boolean put(String k, Object v) {
        if (!regex(k)) {
            return false;
        }
        heap.put(k, v);
        return true;
    }

    public static Object get(String k, String defaultV) {
        if (!regex(k)) {
            return null;
        }
        return heap.getOrDefault(k, defaultV);
    }

    public static boolean clean(String k) {
        if (!regex(k)) {
            return false;
        }
        heap.remove(k);
        return true;
    }

    public static boolean regex(String k) {
        return pattern.matcher(k).matches();

    }
}

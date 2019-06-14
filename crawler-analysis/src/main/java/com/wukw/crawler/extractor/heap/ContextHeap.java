package com.wukw.crawler.extractor.heap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class ContextHeap extends BaseHeap {

    private ContextHeap() {
        super(new ConcurrentHashMap(), Pattern.compile("$\\{}"));
    }
}

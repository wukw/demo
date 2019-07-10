package com.wukw.crawler.extractor.heap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class ContextHeap extends BaseHeap {

    public ContextHeap() {
        super(
                new ThreadLocal<ConcurrentHashMap>(),
                Pattern.compile("\\$\\{.*}"),
                Pattern.compile(".*\\$\\{(.*)}.*"),
                Pattern.compile(".*(\\$\\{.*}).*")
        );
    }
}

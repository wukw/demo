package com.wukw.crawler.extractor.heap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class PageHeap extends BaseHeap {

    private PageHeap() {
        super(new ConcurrentHashMap(), Pattern.compile("#\\{}"));
    }

}

package com.wukw.crawler.extractor.heap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class PageHeap extends BaseHeap {

    public PageHeap() {
        super(new ThreadLocal<ConcurrentHashMap>(),
                Pattern.compile("#\\{.*}"),
                Pattern.compile(".*#\\{(.*)}.*"),
                Pattern.compile(".*(#\\{.*}).*")
        );
    }

}

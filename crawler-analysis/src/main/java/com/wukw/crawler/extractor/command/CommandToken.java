package com.wukw.crawler.extractor.command;

import com.wukw.crawler.extractor.heap.ContextHeap;
import com.wukw.crawler.extractor.heap.PageHeap;

public interface CommandToken<T> {


    public T doCommmand(String command);
}

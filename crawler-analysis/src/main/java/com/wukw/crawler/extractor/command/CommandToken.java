package com.wukw.crawler.extractor.command;

public interface CommandToken<T, V> {


    public V doCommmand(T command);

}

package com.wukw.crawler.model;

public abstract class HttpResponseFormatBody<E,A> {
    public abstract E element(String element);

    public abstract String attr(String attr);
}

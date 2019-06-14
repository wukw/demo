package com.wukw.crawler.Exception;

import lombok.Builder;
import lombok.Data;

@Data
public class HeapException extends RuntimeException {
    public String msg;

    public HeapException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}

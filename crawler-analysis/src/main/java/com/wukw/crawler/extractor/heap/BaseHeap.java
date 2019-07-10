package com.wukw.crawler.extractor.heap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseHeap implements HeapInterface {
    ThreadLocal<ConcurrentHashMap> heap;
    Pattern pattern;
    Pattern namePattern;
    Pattern relacePattern;

    public BaseHeap(ThreadLocal<ConcurrentHashMap> concurrentHashMap, Pattern p, Pattern n, Pattern r) {
        heap = concurrentHashMap;
        pattern = p;
        namePattern = n;
        relacePattern = r;
    }

    public boolean put(String k, Object v) {
        if (!regex(k)) {
            return false;
        }
        String keyName = getKeyName(k);
        heap.get().put(keyName, v);
        return true;
    }

    public String replace(String text) {
        for (Matcher matcher = relacePattern.matcher(text); matcher.find(); ) {
            String name = matcher.group(1);
            Object heapValue = null;
            if ((heapValue = get(name)) != null) {
                text = text.replace(name, heapValue.toString());
            }
        }
        return text;
    }

    private String getKeyName(String text) {
        Matcher matcher = namePattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }

    public Object get(String k) {
        if (!regex(k)) {
            return null;
        }
        String keyName = getKeyName(k);
        String defaultV = null;
        if (keyName.contains(":")) {
            defaultV = keyName.split(":")[1];
        }
        return getByName(keyName, defaultV);
    }

    private Object getByName(String name, String defaultV) {
        return heap.get().getOrDefault(name, defaultV);
    }


    public boolean clean(String k) {
        if (!regex(k)) {
            return false;
        }
        heap.get().remove(k);
        return true;
    }

    private boolean regex(String k) {
        return pattern.matcher(k).matches();

    }

    @Override
    public void threadInit() {
        heap.set(new ConcurrentHashMap());

    }

    @Override
    public void threadDestroy() {
        heap.remove();
    }
}

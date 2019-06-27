package com.wukw.crawler.extractor.heap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseHeap {
    ConcurrentHashMap<String, Object> heap;
    Pattern pattern;
    Pattern namePattern;
    Pattern relacePattern;

    public BaseHeap(ConcurrentHashMap concurrentHashMap, Pattern p, Pattern n,Pattern r) {
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
        heap.put(keyName, v);
        return true;
    }

    public String replace(String text) {
        for (Matcher matcher = relacePattern.matcher(text); matcher.find(); ) {
            String name = matcher.group(1);
            text = text.replace(name,get(name).toString());
        }
        return text;
    }

    private String getKeyName(String k) {
        Matcher matcher = namePattern.matcher(k);
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

    public Object getByName(String name, String defaultV) {
        return heap.getOrDefault(name, defaultV);
    }


    public boolean clean(String k) {
        if (!regex(k)) {
            return false;
        }
        heap.remove(k);
        return true;
    }

    private boolean regex(String k) {
        return pattern.matcher(k).matches();

    }
}

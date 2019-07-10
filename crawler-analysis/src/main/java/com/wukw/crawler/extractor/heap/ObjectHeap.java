package com.wukw.crawler.extractor.heap;

import lombok.AllArgsConstructor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * var对象的存储 单例
 */

public class ObjectHeap implements HeapInterface {
    static ObjectHeap objectHeap = new ObjectHeap();

    private ObjectHeap() {
    }

    public static ObjectHeap getInstance() {
        return objectHeap;
    }

    @AllArgsConstructor
    private class NameCountKey {
        String name;
        String aliasName;
        int index;

        @Override
        public int hashCode() {
            return name.hashCode() + index;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof NameCountKey) {
                NameCountKey nameCountKey = (NameCountKey) obj;
                return this.name.equals(nameCountKey.name) && this.aliasName.equals(((NameCountKey) obj).aliasName) && this.index == nameCountKey.index;
            }
            return false;
        }
    }


    static ThreadLocal<ConcurrentHashMap<NameCountKey, Object>> objectPool = new ThreadLocal<>();
    //相同名称的计数器
    static ThreadLocal<ConcurrentHashMap<String, AtomicInteger>> objectCount = new ThreadLocal<>();

    /**
     * @param name      全额限定名
     * @param aliasName 别名
     * @param obj       对象
     * @param limit     偏移量
     */
    public void addObject(String name, String aliasName, Object obj, int limit) {
        Integer oc = objectCount.get().getOrDefault(name + aliasName, new AtomicInteger(0)).intValue();
        objectPool.get().put(new NameCountKey(name, aliasName, oc + limit), obj);
        //数量加一
        objectCount.get().put(name + aliasName, new AtomicInteger(objectCount.get().getOrDefault(name, new AtomicInteger(0)).incrementAndGet()));
    }

    /**
     * @param name
     * @param aliasName
     * @param index     位置
     * @return
     */
    public Object getObject(String name, String aliasName, int index) {
        return objectPool.get().get(new NameCountKey(name, aliasName, index));
    }


    @Override
    public void threadInit() {
        objectPool.set(new ConcurrentHashMap<>());
        objectCount.set(new ConcurrentHashMap<>());

    }

    @Override
    public void threadDestroy() {
        objectPool.remove();
        objectCount.remove();

    }
}

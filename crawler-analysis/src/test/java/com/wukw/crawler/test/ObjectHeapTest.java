package com.wukw.crawler.test;

import com.wukw.crawler.extractor.heap.ObjectHeap;
import com.wukw.crawler.model.TestModel;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ObjectHeapTest {
    @Test
    public void test() {
        ObjectHeap.getInstance().threadInit();

        ObjectHeap.getInstance().addObject("com.wukw.crawler.model.TestModel", "model", new TestModel(), 0);
        assertNotNull(ObjectHeap.getInstance().getObject("com.wukw.crawler.model.TestModel", "model", 0));

        ObjectHeap.getInstance().addObject("com.wukw.crawler.model.TestModel1", "", new TestModel(), 0);
        assertNotNull(ObjectHeap.getInstance().getObject("com.wukw.crawler.model.TestModel1", "", 0));
    }
}

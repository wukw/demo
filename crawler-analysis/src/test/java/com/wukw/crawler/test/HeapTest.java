package com.wukw.crawler.test;

import com.wukw.crawler.extractor.heap.HeapUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HeapTest {
    /**
     * 测试 存放 符号值替换
     */
    @Test
    public void testReplace() {
        //context测试
        HeapUtils.put("${a}", "a");
        assertEquals("a", HeapUtils.get("${a}"));
        assertEquals(null, HeapUtils.get("${b}"));
        assertEquals("abbbb", HeapUtils.replace( "${a}bbbb"));
        assertEquals("abbbba", HeapUtils.replace("${a}bbbb${a}"));
        //page测试
        HeapUtils.put("#{p}", "l");
        assertEquals("l", HeapUtils.get("#{p}"));
        assertEquals(null, HeapUtils.get("#{c}"));
        assertEquals("lbbbb", HeapUtils.replace( "#{p}bbbb"));
        assertEquals("lbbbbl", HeapUtils.replace("#{p}bbbb#{p}"));
    }
}

package com.wukw.crawler.test;

import com.wukw.crawler.extractor.command.ConditionToken;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConditonTest {
    @Test
    public void conditionTest() {
        boolean first = ConditionToken.conditionToken.doCommmand("1=1");
        boolean second = ConditionToken.conditionToken.doCommmand("1=2");
        boolean three = ConditionToken.conditionToken.doCommmand("1=2&&1=1");
        boolean four = ConditionToken.conditionToken.doCommmand("1=2||1=1");
        boolean five = ConditionToken.conditionToken.doCommmand("1=2||1=1&&1=1");
        boolean six = ConditionToken.conditionToken.doCommmand("1=1&&1=2||1=1&&1=1");
        boolean seven = ConditionToken.conditionToken.doCommmand("1=1&&1=2||1=1&&1=2");
        assertEquals(true, first);
        assertEquals(false, second);
        assertEquals(false, three);
        assertEquals(true, four);
        assertEquals(true, five);
        assertEquals(true, six);
        assertEquals(false, seven);


    }

}

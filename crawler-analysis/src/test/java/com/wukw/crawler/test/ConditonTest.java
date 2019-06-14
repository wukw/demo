package com.wukw.crawler.test;

import com.wukw.crawler.extractor.command.ConditionCommandToken;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConditonTest {
    @Test
    public void conditionTest(){
        ConditionCommandToken conditionCommandToken = new ConditionCommandToken();
        boolean first = conditionCommandToken.doCommmand("1=1");
        boolean second = conditionCommandToken.doCommmand("1=2");
        boolean three = conditionCommandToken.doCommmand("1=2&&1=1");
        boolean four = conditionCommandToken.doCommmand("1=2||1=1");
        boolean five = conditionCommandToken.doCommmand("1=2||1=1&&1=1");
        boolean six = conditionCommandToken.doCommmand("1=1&&1=2||1=1&&1=1");
        boolean seven = conditionCommandToken.doCommmand("1=1&&1=2||1=1&&1=2");
        assertEquals(true,first);
        assertEquals(false,second);
        assertEquals(false,three);
        assertEquals(true,four);
        assertEquals(true,five);
        assertEquals(true,six);
        assertEquals(false,seven);




    }

}

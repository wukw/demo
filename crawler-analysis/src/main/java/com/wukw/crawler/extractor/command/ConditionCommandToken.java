package com.wukw.crawler.extractor.command;

import com.wukw.crawler.extractor.heap.HeapUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 条件语句
 */
public class ConditionCommandToken implements CommandToken<Boolean> {

    private static final Pattern AND_OR = Pattern.compile("([\\s\\S]*?)([&|]{2})");


    @Override
    public Boolean doCommmand(String command) {
        int start = 0;
        for (Matcher matcher = AND_OR.matcher(command); matcher.find(); start = matcher.end()) {
            String subCondition = matcher.group(1);
            boolean subResult = this.verifyCondition(subCondition);
            boolean isAnd = matcher.group(2).equalsIgnoreCase("&&");
            // false  &&
            if (!subResult && isAnd) {
                return false;
            }
        }
        return verifyCondition(command.substring(start));

    }

    public boolean verifyCondition(String condition) {
        String[] leftAndRight = condition.split("=");
        String left = leftAndRight[0];
        String right = leftAndRight[1];

        Object lV = HeapUtils.get(left);
        Object rV = HeapUtils.get(right);
        return lV.equals(rV);
    }
}

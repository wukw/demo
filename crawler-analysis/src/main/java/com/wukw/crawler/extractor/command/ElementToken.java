package com.wukw.crawler.extractor.command;

import com.wukw.crawler.model.HttpResponseFormatBody;
import lombok.AllArgsConstructor;

import java.util.Stack;

@AllArgsConstructor
public class ElementToken implements CommandToken<Stack<Object>, String> {
    String element;
    @Override
    public String doCommmand(Stack<Object> stack) {
        if (stack.peek() instanceof HttpResponseFormatBody) {
            HttpResponseFormatBody body = (HttpResponseFormatBody) stack.peek();
            return body.element(element);
        } else {
            return null;
        }

    }
}

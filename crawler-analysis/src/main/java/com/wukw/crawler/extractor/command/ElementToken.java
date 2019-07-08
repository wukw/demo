package com.wukw.crawler.extractor.command;

import com.wukw.crawler.model.HttpResponseFormatBody;
import com.wukw.crawler.model.HttpResponseFormatHtml;
import lombok.AllArgsConstructor;
import org.jsoup.select.Elements;

import java.util.Stack;

@AllArgsConstructor
public class ElementToken implements CommandToken<Stack<Object>, Object> {
    String element;


    @Override
    public HttpResponseFormatBody doCommmand(Stack<Object> stack) {
        if (stack.peek() instanceof HttpResponseFormatBody) {
            HttpResponseFormatBody body = (HttpResponseFormatBody) stack.peek();
            Object e = body.element(element);
            HttpResponseFormatBody httpResponseFormatBody = new HttpResponseFormatHtml((Elements) e);
            return httpResponseFormatBody;
        } else {
            return null;
        }

    }

    public String attr(Stack<Object> stack) {
        if (stack.peek() instanceof HttpResponseFormatBody) {
            HttpResponseFormatBody body = (HttpResponseFormatBody) stack.peek();
            String value = body.attr(element);
            stack.push(value);
            return value;
        } else {
            return null;
        }


    }
}

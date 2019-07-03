package com.wukw.crawler.extractor.command;

import com.wukw.crawler.extractor.heap.HeapUtils;
import com.wukw.crawler.model.HttpResponse;
import com.wukw.crawler.model.HttpResponseFormatBody;
import com.wukw.crawler.model.config.HttpPageResponse;
import com.wukw.crawler.model.config.HttpPageResponseExtractor;
import com.wukw.crawler.model.config.HttpPageResponseExtractorsStroe;

import java.util.Map;
import java.util.Stack;

public class HttpPageResponseExtractsToken implements CommandToken<HttpPageResponse, Map<String, String>>{

    @Override
    public Map<String, String> doCommmand(HttpPageResponse httpPageResponse) {
        HttpResponse httpResponse = (HttpResponse) HeapUtils.getThread("HttpResponse");

        for (HttpPageResponseExtractor httpPageResponseExtractor : httpPageResponse.getHttpPageResponseExtractors()) {
            Stack<Object> stack = new Stack<>();
            formatType(httpPageResponseExtractor.getType(),stack,httpResponse.getBody());
            ElementToken elementToken = new ElementToken(httpPageResponseExtractor.getElement());
            elementToken.doCommmand(stack);
            for(HttpPageResponseExtractorsStroe stroe : httpPageResponseExtractor.getStroes()){
                new StoreToken(stroe).doCommmand(stack);
            }
        }
        return null;
    }

    /**
     *
     * @param type
     * @param stack
     * @param body
     * @return
     */
    public HttpResponseFormatBody formatType(String type, Stack<Object> stack, String body){
        HttpResponseFormatBody formatObject = null;
        if("JSON".equalsIgnoreCase(type)){
            //todo json fromat
        }
        if("HTML".equalsIgnoreCase(type)){
            //todo html fromat
        }
        stack.push(body);
        return null;
    }
}

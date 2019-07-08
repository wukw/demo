package com.wukw.crawler.extractor.command;

import com.alibaba.fastjson.JSON;
import com.wukw.crawler.model.HttpResponse;
import com.wukw.crawler.model.HttpResponseFormatBody;
import com.wukw.crawler.model.HttpResponseFormatHtml;
import com.wukw.crawler.model.HttpResponseFormatJson;
import com.wukw.crawler.model.config.HttpPageResponse;
import com.wukw.crawler.model.config.HttpPageResponseExtractor;
import com.wukw.crawler.model.config.HttpPageResponseExtractorsStroe;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Map;
import java.util.Stack;

@AllArgsConstructor
public class HttpPageResponseExtractsToken implements CommandToken<HttpResponse, Map<String, String>> {
    HttpPageResponse httpPageResponse;

    @Override
    public Map<String, String> doCommmand(HttpResponse httpResponse) {

        for (HttpPageResponseExtractor httpPageResponseExtractor : httpPageResponse.getHttpPageResponseExtractors()) {
            Stack<Object> stack = new Stack<>();
            stack.push(httpResponse.getBody() == null ? "" : httpResponse.getBody());
            formatType(httpPageResponseExtractor.getType(), stack);
            new ElementToken(httpPageResponseExtractor.getElement()).doCommmand(stack);
            for (HttpPageResponseExtractorsStroe stroe : httpPageResponseExtractor.getStroes()) {
                new StoreToken(stroe).doCommmand(stack);
            }
        }
        return null;
    }

    /**
     * @param type  JSON|HTML
     * @param stack
     * @return
     */
    public HttpResponseFormatBody formatType(String type, Stack<Object> stack) {
        Object body = stack.peek();
        //转 json
        HttpResponseFormatBody formatObject = null;
        if ("JSON".equalsIgnoreCase(type)) {
            Map jsonMap = JSON.parseObject(body.toString(), Map.class);
            formatObject = new HttpResponseFormatJson(jsonMap);
        }
        //转 html
        if ("HTML".equalsIgnoreCase(type)) {
            Elements document = Jsoup.parse(body.toString()).children();
            formatObject = new HttpResponseFormatHtml(document);
        }
        if (formatObject != null) {
            stack.push(formatObject);
        }
        return formatObject;
    }
}

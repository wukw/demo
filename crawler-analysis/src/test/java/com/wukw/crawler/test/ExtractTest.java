package com.wukw.crawler.test;

import com.wukw.crawler.extractor.command.HttpPageResponseExtractsToken;
import com.wukw.crawler.extractor.command.HttpPageResponseToken;
import com.wukw.crawler.extractor.heap.HeapUtils;
import com.wukw.crawler.model.HttpResponse;
import com.wukw.crawler.model.TestModel;
import com.wukw.crawler.model.config.HttpPageResponse;
import com.wukw.crawler.model.config.HttpPageResponseExtractor;
import com.wukw.crawler.model.config.HttpPageResponseExtractorsStroe;
import com.wukw.crawler.model.config.HttpPageVar;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class ExtractTest {
    @Test
    public void test() {
        HeapUtils.init();
        HttpPageResponseExtractorsStroe httpPageResponseExtractorsStroe = new HttpPageResponseExtractorsStroe();

        List<HttpPageResponseExtractorsStroe> httpPageResponseExtractorsStroeList = new ArrayList();
        httpPageResponseExtractorsStroeList.add(httpPageResponseExtractorsStroe);
        httpPageResponseExtractorsStroe.setElement("tr");
        httpPageResponseExtractorsStroe.setObjectField("a");
        httpPageResponseExtractorsStroe.setObjectName("model0");

        HttpPageResponseExtractor httpPageResponseExtractor = new HttpPageResponseExtractor();
        httpPageResponseExtractor.setStroes(httpPageResponseExtractorsStroeList);
        httpPageResponseExtractor.setElement("table");
        httpPageResponseExtractor.setType("HTML");
        httpPageResponseExtractor.setStroes(httpPageResponseExtractorsStroeList);
        List<HttpPageResponseExtractor> httpPageResponseExtractorList = new ArrayList<>();
        httpPageResponseExtractorList.add(httpPageResponseExtractor);

        HttpPageVar var = new HttpPageVar();
        var.setClazz("com.wukw.crawler.model.TestModel");
        var.setBeanName("model");
        httpPageResponseExtractor.setVar(var);

        HttpPageResponse httpPageResponse = new HttpPageResponse();
        httpPageResponse.setHttpPageResponseExtractors(httpPageResponseExtractorList);

        HttpResponse httpResponse = HttpResponse.builder().body("<table><tr><td>user</td><td>cc</td></tr><tr><td>pass</td><td>123</td></tr></table>").build();

        //------------------测试用例

        HttpPageResponseToken httpPageResponseToken = new HttpPageResponseToken();
        HttpPageResponseExtractsToken httpPageResponseExtractsToken = new HttpPageResponseExtractsToken(httpPageResponse);
        httpPageResponseExtractsToken.doCommmand(httpResponse);
        assertEquals("user cc pass 123", ((TestModel) HeapUtils.getObj("model0")).getA());


    }
}

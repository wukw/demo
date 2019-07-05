package com.wukw.crawler.test;

import com.wukw.crawler.extractor.command.HttpPageRequestToken;
import com.wukw.crawler.extractor.command.HttpPageResponseExtractsToken;
import com.wukw.crawler.extractor.command.HttpPageResponseToken;
import com.wukw.crawler.model.HttpResponse;
import com.wukw.crawler.model.config.HttpPageResponse;
import com.wukw.crawler.model.config.HttpPageResponseExtractor;
import com.wukw.crawler.model.config.HttpPageResponseExtractorsStroe;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ExtractTest {
    @Test
    public void test() {

        HttpPageResponseExtractorsStroe httpPageResponseExtractorsStroe = new HttpPageResponseExtractorsStroe();

        List<HttpPageResponseExtractorsStroe> httpPageResponseExtractorsStroeList = new ArrayList();
        httpPageResponseExtractorsStroeList.add(httpPageResponseExtractorsStroe);
        httpPageResponseExtractorsStroe.setElement("div#a");
        httpPageResponseExtractorsStroe.setObjectField("a");
        httpPageResponseExtractorsStroe.setObjectName("model");

        HttpPageResponseExtractor httpPageResponseExtractor = new HttpPageResponseExtractor();
        httpPageResponseExtractor.setStroes(httpPageResponseExtractorsStroeList);
        httpPageResponseExtractor.setElement("table");
        httpPageResponseExtractor.setType("HTML");
        httpPageResponseExtractor.setStroes(httpPageResponseExtractorsStroeList);
        List<HttpPageResponseExtractor> httpPageResponseExtractorList = new ArrayList<>();
        httpPageResponseExtractorList.add(httpPageResponseExtractor);

        HttpPageResponse httpPageResponse = new HttpPageResponse();
        httpPageResponse.setHttpPageResponseExtractors(httpPageResponseExtractorList);

        HttpResponse httpResponse =  HttpResponse.builder().body("<table><a></a></table>").build();

        //------------------测试用例
        HttpPageResponseToken httpPageResponseToken = new HttpPageResponseToken();

        HttpPageResponseExtractsToken httpPageResponseExtractsToken = new HttpPageResponseExtractsToken(httpPageResponse);
        httpPageResponseExtractsToken.doCommmand(httpResponse);




    }
}

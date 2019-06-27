package com.wukw.crawler.extractor.command;

import com.wukw.crawler.model.config.HttpPageRequest;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.util.Map;

public class HttpRequestCommandToken implements CommandToken<HttpPageRequest, Map> {
    @Override
    public Map doCommmand(HttpPageRequest pageRequest) {
       /* OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(pageRequest.getUrl())
                .method(pageRequest.getMethod(),null);*/
       return null;

    }




}

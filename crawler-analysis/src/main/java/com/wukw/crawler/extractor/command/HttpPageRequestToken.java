package com.wukw.crawler.extractor.command;

import com.wukw.crawler.extractor.heap.HeapUtils;
import com.wukw.crawler.model.HttpResponse;
import com.wukw.crawler.model.config.HttpPageRequest;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import javax.net.ssl.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Slf4j
public class HttpPageRequestToken implements CommandToken<HttpPageRequest, HttpResponse> {
    public static HttpPageRequestToken httpPageRequestToken = new HttpPageRequestToken();

    private HttpPageRequestToken() {
    }


    @Override
    public HttpResponse doCommmand(HttpPageRequest pageRequest) {

        OkHttpClient okHttpClient = getUnsafeOkHttpClient();
        final Request request = getRequest(pageRequest);
        try (Response response = okHttpClient.newCall(request).execute()) {
            return getPageResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private HttpResponse getPageResponse(Response response) {
        try {
            return HttpResponse.builder().body(response.body().string()).headers(response.headers().toMultimap()).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }

    private Map<String, String> getHeaders(HttpPageRequest httpPageRequest) {
        Map<String, String> pageRequestHeaders = httpPageRequest.getHeaders();
        Set<String> keySet = pageRequestHeaders.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = pageRequestHeaders.get(key);
            pageRequestHeaders.put(key, HeapUtils.replace(value));
        }
        return pageRequestHeaders;
    }

    private Request getRequest(HttpPageRequest pageRequest) {
        String url = HeapUtils.replace(pageRequest.getUrl());
        Map<String, String> pageRequestHeaders = this.getHeaders(pageRequest);
        HttpUrl httpUrl = HttpUrl.parse(url);
        Headers headers = Headers.of(pageRequestHeaders);
        String body = null;
        MediaType mediaType = null;
        if (!pageRequest.getMethod().equalsIgnoreCase("GET")) {
            body = HeapUtils.replace(pageRequest.getBody());
            mediaType = MediaType.parse(pageRequest.getMediaType());
        }
        switch (pageRequest.getMethod().toUpperCase()) {
            case "GET":
                return new Request.Builder().url(httpUrl).headers(headers).build();
            case "POST":
                return new Request.Builder().url(httpUrl).headers(headers).method("POST", RequestBody.create(body, mediaType)).build();
        }
        return null;
    }


    /**
     * 解决 证书问题
     *
     * @return
     */
    private OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

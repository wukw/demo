package com.wukw.crawler.extractor.command;

import com.wukw.crawler.model.config.HttpPage;
import com.wukw.crawler.model.config.HttpPageFor;

public class HttpPageToken implements CommandToken<HttpPage, Boolean> {
    private HttpPageToken() {
    }

    static HttpPageToken httpPageToken = new HttpPageToken();

    @Override
    public Boolean doCommmand(HttpPage httpPage) {
        if (httpPage.getHttpPageFor() != null) {
            HttpPageFor pageFor = httpPage.getHttpPageFor();
            for (int start = pageFor.getStart(); start <= pageFor.getEnd(); start = start + pageFor.getStep()) {
                HttpPageRequestToken.httpPageRequestToken.doCommmand(httpPage.getRequest());
            }
        } else {
            HttpPageRequestToken.httpPageRequestToken.doCommmand(httpPage.getRequest());
        }
        return true;
    }
}

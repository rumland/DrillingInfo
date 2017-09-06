package com.saresource.drillinginfo.directaccess;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class GetProductionHeadersPage implements Callable<DrillingInfoData> {
    private final String url;
    private final String API_KEY;
    private final HttpClient client;

    GetProductionHeadersPage(String url) {
        this.url = url;
        this.API_KEY = System.getenv("DRILLING_INFO_API_KEY");
        this.client = HttpClientBuilder.create().build();
    }

    @Override
    public DrillingInfoData call() throws Exception {
        return getProductionHeadersPage();
    }

    private DrillingInfoData getProductionHeadersPage() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String json = executeRestQuery();
        stopWatch.stop();

        return new DrillingInfoData(DrillingInfoDataFormat.JSON, json);
    }

    String executeRestQuery() {
        StopWatch httpGetStopWatch = new StopWatch();
        httpGetStopWatch.start();
        HttpGet get = new HttpGet(url);
        get.setHeader("X-API-KEY", API_KEY);
        get.setHeader("Connection", "close");
        HttpResponse response;
        String json;
        try {
            response = client.execute(get);
            httpGetStopWatch.stop();
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                String message = String.format("Failed to issue request (%s:%s)", url, statusCode);
                if (statusCode == HttpStatus.SC_GATEWAY_TIMEOUT) {
                    System.out.println(message);
                    try {
                        TimeUnit.MILLISECONDS.sleep(750);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Trying 1 more time after waiting 750ms...");
                    return executeRestQuery();
                }
                throw new RuntimeException(message);
            }

            HttpEntity entity = response.getEntity();
            json = EntityUtils.toString(entity);
            get.releaseConnection();
            EntityUtils.consume(entity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return json;
    }
}

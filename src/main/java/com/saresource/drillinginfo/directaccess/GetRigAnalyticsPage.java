package com.saresource.drillinginfo.directaccess;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saresource.drillinginfo.directaccess.pojo.v1.RigAnalytics;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class GetRigAnalyticsPage implements Callable<Collection<RigAnalytics>> {
    private final String url;
    private final String API_KEY;
    private final HttpClient client;
    private final ObjectMapper mapper = new ObjectMapper();

    public GetRigAnalyticsPage(String url) {
        this.url = url;
        this.API_KEY = System.getenv("DRILLING_INFO_API_KEY");
        this.client = HttpClientBuilder.create().build();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public Collection<RigAnalytics> call() throws Exception {
        return getRigAnalyticsPage(url);
    }

    private Collection<RigAnalytics> getRigAnalyticsPage(String url) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            StopWatch httpGetStopWatch = new StopWatch();
            httpGetStopWatch.start();
            HttpGet get = new HttpGet(url);
            get.setHeader("X-API-KEY", API_KEY);
            get.setHeader("Connection", "close");
            HttpResponse response = client.execute(get);
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
                    return getRigAnalyticsPage(url);
                }
                throw new RuntimeException(message);
            }

            HttpEntity entity = response.getEntity();
            String json = EntityUtils.toString(entity);
            get.releaseConnection();
            EntityUtils.consume(entity);
            Collection<RigAnalytics> productionHeaders = parseJson(json);
            stopWatch.stop();

            return productionHeaders;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Collection<RigAnalytics> parseJson(String json) {
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            RigAnalytics[] productionHeaders = mapper.readValue(json, RigAnalytics[].class);
            List<RigAnalytics> headers = Arrays.asList(productionHeaders);
            stopWatch.stop();
            return headers;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

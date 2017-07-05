package com.saresource.drillinginfo.directaccess;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saresource.drillinginfo.directaccess.pojo.v1.ProductionHeader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class DrillingInfoDirectAccess {
    private final String API_KEY;
    private final String baseUrl = "https://di-api.drillinginfo.com/v1/direct-access";
    private final String productionHeaderUrlFormat = String.format("%s/%s",
            baseUrl, "producing-entities?state_province=%s&format=json&page=%s&pagesize=100&current_producing_status=ACTIVE");

    private final ObjectMapper mapper = new ObjectMapper();

    private final HttpClient client;

    public DrillingInfoDirectAccess(String API_KEY) {
        this.API_KEY = API_KEY;
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        client = HttpClientBuilder.create().build();
    }

    /**
     * Get production headers for state or providence 100 at a time. For time being, limit to N headers.
     * @param stateOrProvince to get production headers for
     * @return all production headers for state or province
     */
    public Collection<ProductionHeader> getProductionHeaders(String stateOrProvince) {
        return getProductionHeaders(stateOrProvince, 90000);
    }

    Collection<ProductionHeader> getProductionHeaders(String stateOrProvince, int headerLimit) {
        Collection<ProductionHeader> allProductionHeaders = new ArrayList<>();
        int page = 1;

        String url = String.format(productionHeaderUrlFormat, stateOrProvince, page);

        Collection<ProductionHeader> productionHeadersPage = getProductionHeadersPage(url);
        while (productionHeadersPage.size() > 0) {
            allProductionHeaders.addAll(productionHeadersPage);
            url = String.format(productionHeaderUrlFormat, stateOrProvince, ++page);
            productionHeadersPage = getProductionHeadersPage(url);
            if (allProductionHeaders.size() > headerLimit) {
                System.out.println("Only getting first " + headerLimit + " production headers");
                break;
            } else {
                String message = String.format("Added %s headers. Have %s in total.",
                        productionHeadersPage.size(), allProductionHeaders.size());
                System.out.println(message);
            }
        }

        return allProductionHeaders;
    }

    private Collection<ProductionHeader> getProductionHeadersPage(String url) {
        try {
            HttpGet get = new HttpGet(url);
            get.setHeader("X-API-KEY", API_KEY);
            get.setHeader("Connection", "close");
            HttpResponse response = client.execute(get);
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
                    return getProductionHeadersPage(url);
                }
                throw new RuntimeException(message);
            }

            HttpEntity entity = response.getEntity();
            String json = EntityUtils.toString(entity);
            get.releaseConnection();
            EntityUtils.consume(entity);
            return parseJson(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Collection<ProductionHeader> parseJson(String json) {
        try {
            ProductionHeader[] productionHeaders = mapper.readValue(json, ProductionHeader[].class);
            return Arrays.asList(productionHeaders);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

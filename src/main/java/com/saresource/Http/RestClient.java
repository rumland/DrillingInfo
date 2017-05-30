package com.saresource.Http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class RestClient {
    private final HttpClient client;

    public RestClient() {
        client = HttpClients.createDefault();
    }

    public HttpResponse GetRequest(HttpUriRequest httpUriRequest) {
        HttpResponse response = null;
        try {
            response = client.execute(httpUriRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}

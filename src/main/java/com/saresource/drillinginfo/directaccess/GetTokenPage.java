package com.saresource.drillinginfo.directaccess;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saresource.drillinginfo.directaccess.pojo.v1.Token;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.Callable;

public class GetTokenPage implements Callable<Token> {
    private final String API_KEY;
    private final String CLIENT_ID;
    private final String CLIENT_SECRET;
    private final HttpClient client;
    private final ObjectMapper mapper = new ObjectMapper();

    GetTokenPage() {
        this.API_KEY = System.getenv("DRILLING_INFO_API_KEY");
        this.CLIENT_ID = System.getenv("DRILLING_INFO_CLIENT_ID");
        this.CLIENT_SECRET = System.getenv("DRILLING_INFO_CLIENT_SECRET");
        this.client = HttpClientBuilder.create().build();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public Token call() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            StopWatch httpPostStopWatch = new StopWatch();
            httpPostStopWatch.start();
            String url = "https://di-api.drillinginfo.com/v2/direct-access/tokens";
            HttpPost post = new HttpPost(url);
            post.setHeader("X-API-KEY", API_KEY);
            post.setHeader("Content-type", "application/x-www-form-urlencoded");
            post.setHeader("Authorization", "Basic " +
                    Base64.getEncoder().encodeToString(
                            String.format("%s:%s", CLIENT_ID, CLIENT_SECRET).getBytes()));
            List<NameValuePair> arguments = new ArrayList<>();
            arguments.add(new BasicNameValuePair("grant_type", "client_credentials"));
            post.setEntity(new UrlEncodedFormEntity(arguments));
            HttpResponse response = client.execute(post);
            httpPostStopWatch.stop();

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                String message = String.format("Failed to issue request (%s:%s)", url, statusCode);
                throw new RuntimeException(message);
            }

            HttpEntity entity = response.getEntity();
            String json = EntityUtils.toString(entity);
            post.releaseConnection();
            EntityUtils.consume(entity);
            Token token = mapper.readValue(json, Token.class);
            stopWatch.stop();

            return token;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.saresource.Http;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class RestClientTest {
    @Test
    public void testGetRequest() {
        //Authentication blog: http://www.baeldung.com/httpclient-4-basic-authentication
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials
                = new UsernamePasswordCredentials("robert@umlandweb.com", "tw4zPdtQ53sB");
        provider.setCredentials(AuthScope.ANY, credentials);

        HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();

        try {
            HttpResponse response = client.execute(
                    new HttpGet("http://api.wunderground.com/api/797e6e66a4fc8809/conditions/q/CO/Fort_Collins.json"));
            int statusCode = response.getStatusLine().getStatusCode();

            Assert.assertEquals(statusCode, HttpStatus.SC_OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
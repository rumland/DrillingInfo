package com.saresource.weatherunderground;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saresource.weatherunderground.Pojo.WeatherUnderground;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class WeatherUndergroundApacheHttpClientJacksonExampleTest {
    @Test
    public void testGetRequest() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        HttpClient client = HttpClientBuilder.create().build();

        try {
            HttpResponse response = client.execute(
                    new HttpGet("http://api.wunderground.com/api/797e6e66a4fc8809/conditions/q/CO/Fort_Collins.json"));
            int statusCode = response.getStatusLine().getStatusCode();
            Assert.assertEquals(statusCode, HttpStatus.SC_OK);

            HttpEntity entity = response.getEntity();
            String json = EntityUtils.toString(entity);

            WeatherUnderground weatherUnderground = mapper.readValue(json, WeatherUnderground.class);
            Assert.assertEquals("0.1", weatherUnderground.getResponse().getVersion());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
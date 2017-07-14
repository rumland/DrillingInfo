package com.saresource.drillinginfo.directaccess;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saresource.drillinginfo.directaccess.pojo.v1.ProducingEntityStats;
import com.saresource.drillinginfo.directaccess.pojo.v1.Token;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;

public class GetBulkProducingEntityStatsPage implements Callable<Collection<ProducingEntityStats>> {
    private final String API_KEY;
    private final HttpClient client;
    private final ObjectMapper mapper = new ObjectMapper();

    GetBulkProducingEntityStatsPage() {
        this.API_KEY = System.getenv("DRILLING_INFO_API_KEY");
        this.client = HttpClientBuilder.create().build();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public List<ProducingEntityStats> call() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        boolean keepGoing = true;
        List<ProducingEntityStats> entityStats = new ArrayList<>();
        String link = "producing-entity-stats";
        while (keepGoing) {
            try {
                StopWatch httpGetStopWatch = new StopWatch();
                httpGetStopWatch.start();

                Token token = (new GetTokenPage()).call();

                String url = "https://di-api.drillinginfo.com/v2/direct-access";
                String uri = String.format("%s/%s?pagesize=%s", url, link, 10000);
                System.out.println("URI: " + uri);
                HttpGet get = new HttpGet(uri);
                get.setHeader("X-API-KEY", API_KEY);
                get.setHeader("Authorization", String.format("Bearer %s", token.getAccess_token()));
                HttpResponse response = client.execute(get);

                Optional<Header> links = Arrays.stream(response.getAllHeaders())
                        .filter(header -> header.getName().equalsIgnoreCase("Links"))
                        .findFirst();
                Header header = links.orElseThrow(() -> new RuntimeException("Couldn't find Links..."));
                List<HeaderElement> headerElements = Arrays.asList(header.getElements());
                for (HeaderElement headerElement : headerElements) {
                    List<NameValuePair> nameValuePairs = Arrays.asList(headerElement.getParameters());
                    for (NameValuePair nameValuePair : nameValuePairs) {
                        String nameValuePairValue = nameValuePair.getValue();
                        if (nameValuePairValue.equals("next")) {
                            String headerElementName = headerElement.getName();
                            String headerElementValue = headerElement.getValue();
                            link = String.format("%s=%s", headerElementName.substring(2), headerElementValue.substring(0, headerElementValue.length() - 1));
                            System.out.println("Found next link: " + link);
                            break;
                        } else {
                            System.out.println("Found: " + nameValuePairValue);
                        }
                    }
                }
                httpGetStopWatch.stop();
                System.out.println("HTTP get took: " + httpGetStopWatch);

                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    String message = String.format("Failed to issue request (%s:%s)", url, statusCode);
                    throw new RuntimeException(message);
                }

                HttpEntity entity = response.getEntity();
                String json = EntityUtils.toString(entity);
                get.releaseConnection();
                EntityUtils.consume(entity);
                ProducingEntityStats[] producingEntityStats = mapper.readValue(json, ProducingEntityStats[].class);

                List<ProducingEntityStats> newEntityStats = Arrays.asList(producingEntityStats);
                if (newEntityStats.size() != 10000) {
                    keepGoing = false;
                }
                entityStats.addAll(newEntityStats);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        stopWatch.stop();

        return entityStats;
    }
}

package com.saresource.drillinginfo.directaccess;

import com.saresource.drillinginfo.directaccess.pojo.v1.ProducingEntityStats;
import com.saresource.drillinginfo.directaccess.pojo.v1.RigAnalytics;
import com.saresource.drillinginfo.directaccess.pojo.v1.Token;
import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DrillingInfoDirectAccess {
    private final String baseUrl = "https://di-api.drillinginfo.com/v1/direct-access";
    private final String productionHeaderUrlFormat = String.format("%s/%s",
            baseUrl, "producing-entities?state_province=%s&format=json&page=%s&pagesize=%s&current_producing_status=ACTIVE");
    private final String rigAnalyticsUrlFormat = String.format("%s/%s",
            baseUrl, "rig-jobs?state=%s&format=json&page=%s&pagesize=%s");

    Token getToken() throws Exception {
        return (new GetTokenPage()).call();
    }

    List<ProducingEntityStats> getAllProducingEntityStats() throws Exception {
        return new GetBulkProducingEntityStatsPage().call();
    }

    public List<ProducingEntityStats> getProducingEntityStats() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<ProducingEntityStats> stats;
        try {
            stats = (new GetBulkProducingEntityStatsPage()).call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Getting " + stats.size() + " stats took: " + stopWatch);

        return stats;
    }

    /**
     * Get production headers for state or providence.
     *
     * @param stateOrProvince to get production headers for
     * @return all production headers for state or province
     */
    public Collection<DrillingInfoData> getProductionHeaders(String stateOrProvince) {
        Collection<DrillingInfoData> allProductionHeaders = new ArrayList<>();

        boolean keepGoing = true;
        int page = 1;
        int batchSize = 5;
        int PAGE_SIZE = 10000;
        while (keepGoing) {
            List<Callable<DrillingInfoData>> callables = new ArrayList<>();
            for (int cnt = 1; cnt <= batchSize; ++cnt) {
                String url = String.format(productionHeaderUrlFormat, stateOrProvince, page, PAGE_SIZE);
                callables.add(new GetProductionHeadersPage(url));
                ++page;
            }

            try {
                ExecutorService executor = Executors.newFixedThreadPool(batchSize);
                executor.invokeAll(callables)
                        .stream()
                        .map(future ->
                        {
                            try {
                                return future.get();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }).forEach(allProductionHeaders::add);

                //TODO ru: determine when full page size _not_ returned...
                keepGoing = false;
//                if (newProductionHeaders.size() != PAGE_SIZE * batchSize) {
//                    keepGoing = false;
//                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return allProductionHeaders;
   }

    /**
     * Get rig analytics for state or providence.
     *
     * @param stateOrProvince to get rig analytics for
     * @return all rig analytics for state or province
     */
    public Collection<RigAnalytics> getRigAnalytics(String stateOrProvince) {
        Collection<RigAnalytics> allRigAnalytics = new ArrayList<>();

        boolean keepGoing = true;
        int page = 1;
        int batchSize = 5;
        int PAGE_SIZE = 10000;
        while (keepGoing) {
            List<Callable<Collection<RigAnalytics>>> callables = new ArrayList<>();
            for (int cnt = 1; cnt <= batchSize; ++cnt) {
                String url = String.format(rigAnalyticsUrlFormat, stateOrProvince, page, PAGE_SIZE);
                callables.add(new GetRigAnalyticsPage(url));
                ++page;
            }

            try {
                ExecutorService executor = Executors.newFixedThreadPool(batchSize);
                Collection<RigAnalytics> newRigAnalytics = new ArrayList<>();
                executor.invokeAll(callables)
                        .stream()
                        .map(future ->
                        {
                            try {
                                return future.get();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }).forEach(newRigAnalytics::addAll);
                allRigAnalytics.addAll(newRigAnalytics);

                if (newRigAnalytics.size() != PAGE_SIZE * batchSize) {
                    keepGoing = false;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return allRigAnalytics;
    }
}

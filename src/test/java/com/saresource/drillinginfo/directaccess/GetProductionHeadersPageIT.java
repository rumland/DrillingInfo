package com.saresource.drillinginfo.directaccess;

import org.junit.Assert;
import org.junit.Test;

public class GetProductionHeadersPageIT {
    @Test
    public void getProductionHeadersJsonTest() {
        String baseUrl = "https://di-api.drillinginfo.com/v1/direct-access";
        String url = String.format("%s/%s", baseUrl, "producing-entities?state_province=CO&format=json&page=1&pagesize=3");

        GetProductionHeadersPage headersPage = new GetProductionHeadersPage(url);
        String entities = headersPage.executeRestQuery();

        Assert.assertFalse("Expected entities.", entities.isEmpty());
    }
}

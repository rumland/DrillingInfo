package com.saresource.drillinginfo.directaccess;

import com.saresource.drillinginfo.directaccess.pojo.v1.ProductionHeader;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

public class DrillingInfoDirectAccessIT {
    private final String API_KEY = "Need to set...";

    @Test
    public void getCoProductionHeadersTest() {
        DrillingInfoDirectAccess diDa = new DrillingInfoDirectAccess(API_KEY);
        Collection<ProductionHeader> productionHeaders = diDa.getProductionHeaders("CO");
        Assert.assertTrue("Expected greater than 0 production headers for CO", productionHeaders.size() > 0);
    }

    @Test
    public void getCaProductionHeadersTest() {
        DrillingInfoDirectAccess diDa = new DrillingInfoDirectAccess(API_KEY);
        Collection<ProductionHeader> productionHeaders = diDa.getProductionHeaders("CA");
        Assert.assertTrue("Expected greater than 0 production headers for CA", productionHeaders.size() > 0);
    }
}
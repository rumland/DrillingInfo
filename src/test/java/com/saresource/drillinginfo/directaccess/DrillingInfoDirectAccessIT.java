package com.saresource.drillinginfo.directaccess;

import com.saresource.drillinginfo.directaccess.pojo.v1.ProductionHeader;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

public class DrillingInfoDirectAccessIT {
    @Test
    public void getCoProductionHeadersTest() {
        DrillingInfoDirectAccess diDa = new DrillingInfoDirectAccess();
        Collection<ProductionHeader> productionHeaders = diDa.getProductionHeaders("CO");
        System.out.println("Production header count: " + productionHeaders.size());
        Assert.assertTrue("Expected greater than 0 production headers for CO",
                productionHeaders.size() > 0);
    }

    @Test
    public void getCaProductionHeadersTest() {
        DrillingInfoDirectAccess diDa = new DrillingInfoDirectAccess();
        Collection<ProductionHeader> productionHeaders = diDa.getProductionHeaders("CA");
        System.out.println("Production header count: " + productionHeaders.size());
        Assert.assertTrue("Expected greater than 0 production headers for CA", productionHeaders.size() > 0);
    }
}
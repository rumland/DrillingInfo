package com.saresource.drillinginfo.directaccess;

import com.saresource.drillinginfo.directaccess.pojo.v1.ProducingEntityStats;
import com.saresource.drillinginfo.directaccess.pojo.v1.ProductionHeader;
import com.saresource.drillinginfo.directaccess.pojo.v1.RigAnalytics;
import com.saresource.drillinginfo.directaccess.pojo.v1.Token;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

public class DrillingInfoDirectAccessIT {
    private DrillingInfoDirectAccess diDa;

    @Before
    public void testSetup() {
        diDa = new DrillingInfoDirectAccess();
    }

    @Test
    public void getToken() throws Exception {
        Token token = diDa.getToken();
        Assert.assertNotNull("Expected token", token);
        Assert.assertFalse("Expected non-empty access token", token.getAccess_token().isEmpty());
        Assert.assertFalse("Expected non-empty expires in", token.getExpires_in().isEmpty());
        Assert.assertFalse("Expected non-empty id", token.getId_token().isEmpty());
        Assert.assertNull("Expected null not before policy", token.getNot_before_policy());
        Assert.assertFalse("Expected non-empty refresh expires in", token.getRefresh_expires_in().isEmpty());
        Assert.assertFalse("Expected non-empty refresh token", token.getRefresh_token().isEmpty());
        Assert.assertFalse("Expected non-empty session state", token.getSession_state().isEmpty());
        Assert.assertFalse("Expected non-empty session state", token.getSession_state().isEmpty());
    }

    @Test
    public void getCaliforniaRigAnalyticsTest() {
        Collection<RigAnalytics> california = diDa.getRigAnalytics("California");
        Assert.assertNotNull("Expected California rig analytics", california);
        Assert.assertFalse("Expected non-zero California rig analytics", california.isEmpty());
    }

    @Test
    public void getAllProducingEntityStatsTest() throws Exception {
        List<ProducingEntityStats> allProducingEntityStats = diDa.getAllProducingEntityStats();
        Assert.assertFalse("Expected producing entities", allProducingEntityStats.isEmpty());
    }

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
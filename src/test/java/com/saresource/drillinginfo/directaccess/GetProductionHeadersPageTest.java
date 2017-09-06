package com.saresource.drillinginfo.directaccess;

//import com.saresource.drillinginfo.directaccess.pojo.v1.ProductionHeader;
//import org.apache.commons.io.IOUtils;
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.util.Collection;

public class GetProductionHeadersPageTest {
    //TODO ru: Should be able to delete. Hoping to remove need to parse return from Drilling-Info entirely.
//    @Test
//    public void parseProductionHeadersTest() throws IOException {
//        String json = IOUtils.toString(this.getClass().getResourceAsStream("productionHeader.json"));
//
//        GetProductionHeadersPage getProductionHeadersPage = new GetProductionHeadersPage("URL");
//        Collection<ProductionHeader> productionHeaders = getProductionHeadersPage.parseJson(json);
//
//        Assert.assertEquals("1 production header expected", 1, productionHeaders.size());
//        ProductionHeader productionHeader = productionHeaders.iterator().next();
//        Assert.assertNotNull(productionHeader);
//        Assert.assertEquals("MESA", productionHeader.getCounty_parish());
//        Assert.assertEquals("X", productionHeader.getAlloc_plus());
//        Assert.assertEquals("050770823300", productionHeader.getApi_awi_list());
//        Assert.assertEquals("05-077-08233-00", productionHeader.getApi_uwi());
//        Assert.assertEquals("PICEANCE", productionHeader.getBasin());
//        Assert.assertEquals("USA", productionHeader.getCountry());
//        Assert.assertEquals("MESA (CO)", productionHeader.getCounty_state());
//        Assert.assertEquals(0, productionHeader.getCum_bcfge());
//        Assert.assertEquals(182425, productionHeader.getCum_gas());
//        Assert.assertEquals(182, productionHeader.getCum_mmcfge());
//        Assert.assertEquals(0, productionHeader.getCum_oil());
//        Assert.assertEquals(3357, productionHeader.getCum_water());
//        Assert.assertEquals("BLACK HILLS PLATEAU PRODUCTION LLC", productionHeader.getCurrent_operator());
//        Assert.assertEquals("MESA", productionHeader.getCounty_parish());
//        Assert.assertEquals("SI", productionHeader.getCurrent_producing_status());
//        Assert.assertEquals(0, productionHeader.getDaily_gas());
//        Assert.assertEquals(0, productionHeader.getDaily_oil());
//        Assert.assertEquals("(N/A)", productionHeader.getDistrict());
//        Assert.assertEquals(104216412, productionHeader.getEntity_id());
//        Assert.assertEquals("39.2476369", String.valueOf(productionHeader.getEntity_latitude()));
//        Assert.assertEquals("-108.239037", String.valueOf(productionHeader.getEntity_longitude()));
//        Assert.assertEquals("WELL", productionHeader.getEntity_type());
//        Assert.assertEquals("SHIRE GULCH", productionHeader.getField());
//        Assert.assertEquals(1965, productionHeader.getFirst_6_boe());
//        Assert.assertEquals(11792, productionHeader.getFirst_6_gas());
//        Assert.assertEquals(0, productionHeader.getFirst_6_oil());
//        Assert.assertEquals(90, productionHeader.getFirst_6_water());
//        Assert.assertEquals(4073, productionHeader.getFirst_12_boe());
//        Assert.assertEquals(24441, productionHeader.getFirst_12_gas());
//        Assert.assertEquals(24, productionHeader.getFirst_12_mmcfge());
//        Assert.assertEquals(0, productionHeader.getFirst_12_oil());
//        Assert.assertEquals(166, productionHeader.getFirst_12_water());
//        Assert.assertEquals("HORSESHOE CANYON", productionHeader.getLease_name());
//    }
}
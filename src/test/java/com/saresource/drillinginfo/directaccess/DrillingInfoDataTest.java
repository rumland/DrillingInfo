package com.saresource.drillinginfo.directaccess;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class DrillingInfoDataTest {
    private DrillingInfoData drillingInfoData;
    private String json;
    private String csv;

    @Before
    public void beforeEachTest() throws IOException {
        json = IOUtils.toString(this.getClass().getResourceAsStream("productionHeader.json"));
        csv = IOUtils.toString(this.getClass().getResourceAsStream("productionHeader.csv"));
        drillingInfoData = new DrillingInfoData(DrillingInfoDataFormat.JSON, json);
    }

    @Test
    public void nativeJsonFormatTest() {
        assertEquals(DrillingInfoDataFormat.JSON, drillingInfoData.getNativeFormat());
    }

    //TODO ru: Finish writting this test. Check the native type and also conversion from JSON to CSV.
    @Test
    public void aggregateConstructorTest() {

    }

    @Test
    public void getJsonRepresentationTest() {
        assertEquals(json, drillingInfoData.serializeAs(DrillingInfoDataFormat.JSON));
    }

    @Test
    public void getCsvRepresentationTest() {
        assertEquals(csv, drillingInfoData.serializeAs(DrillingInfoDataFormat.CSV));
    }
}
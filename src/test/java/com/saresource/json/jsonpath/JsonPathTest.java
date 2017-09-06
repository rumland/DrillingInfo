package com.saresource.json.jsonpath;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JsonPathTest {
    private final InputStream json = this.getClass().getResourceAsStream("production-entities.json");
    private final DocumentContext context = JsonPath.parse(json);

    @Test
    public void readSingleFieldTest() throws IOException {
        assertEquals("16-12V", context.read("$.[0].well_number"));
    }

    @Test
    public void readAllFieldInArrayTest() {
        List<String> read = context.read("$.[*].well_number");
        assertTrue(read.contains("16-12V"));
        assertTrue(read.contains("18-01V"));
        assertTrue(read.contains("14-2"));
    }

    @Test
    public void arrayLengthTest() {
        int length = context.read("$.length()");
        assertEquals(3, length);
    }
}

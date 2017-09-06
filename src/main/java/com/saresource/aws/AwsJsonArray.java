package com.saresource.aws;

import org.apache.commons.io.IOUtils;
import org.json.CDL;
import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class AwsJsonArray {
    private final String json;

    public AwsJsonArray(String json) {
        this.json = json;
    }

    public AwsJsonArray(InputStream inputStream) {
        try {
            json = IOUtils.toString(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCsvString() {
        JSONArray array = new JSONArray(json);
        return CDL.toString(array);
    }
}

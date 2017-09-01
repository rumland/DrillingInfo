package com.saresource.aws;

import org.apache.commons.io.IOUtils;
import org.json.CDL;
import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

class AwsJsonArray {
    private final InputStream inputStream;

    AwsJsonArray(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    String getCsvString() {
        String json;
        try {
            json = IOUtils.toString(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JSONArray array = new JSONArray(json);
        return CDL.toString(array);
    }
}

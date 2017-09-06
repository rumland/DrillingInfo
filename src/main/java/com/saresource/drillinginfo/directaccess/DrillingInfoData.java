package com.saresource.drillinginfo.directaccess;

import com.saresource.aws.AwsJsonArray;
import org.json.JSONArray;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;

public class DrillingInfoData {
    private DrillingInfoDataFormat nativeFormat;
    private String json = null;
    private String csv = null;

    DrillingInfoData(DrillingInfoDataFormat format, String data) {
        nativeFormat = format;
        switch (format) {
            case JSON:
                json = data;
                break;
            case CSV:
                csv = data;
                break;
        }
    }

    public DrillingInfoData(Collection<DrillingInfoData> drillingInfoDatum) {
        nativeFormat = DrillingInfoDataFormat.AGGREGATE;

        JSONArray thisData = new JSONArray();
        for (DrillingInfoData data : drillingInfoDatum) {
            //TODO ru: fix, this caused a OutOfMemoryError :-(
            JSONArray jsonArray = new JSONArray(data.serializeAs(DrillingInfoDataFormat.JSON));
            for (int idx = 0; idx < jsonArray.length(); ++idx) {
                thisData.put(jsonArray.get(idx));
            }
        }

        json = thisData.toString();
    }

    public String serializeAs(DrillingInfoDataFormat format) {
       switch (format) {
            case JSON:
                if (json == null) {
                    json = getDataAsJson();
                }
                return json;
            case CSV:
                if (csv == null) {
                    csv = getDataAsCsv();
                }
                return csv;
        }

        throw new RuntimeException("Unknown format " + format.toString());
    }

    private String getDataAsJson() {
        throw new NotImplementedException();
    }

    private String getDataAsCsv() {
        AwsJsonArray awsJsonArray = new AwsJsonArray(json);
        return awsJsonArray.getCsvString();
    }

    /**
     * Return format of data originally given to constructor. Intended use is for performance since non-native data
     * formats are built on demand.
     * @return format of data used to construct this instance
     */
    DrillingInfoDataFormat getNativeFormat() {
        return nativeFormat;
    }
}

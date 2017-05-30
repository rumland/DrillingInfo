package com.saresource.weatherunderground.Pojo;

public class WeatherUndergroundResponse {
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTermsofService() {
        return termsofService;
    }

    public void setTermsofService(String termsofService) {
        this.termsofService = termsofService;
    }

    public WeatherUndergroundResponseFeatures getFeatures() {
        return features;
    }

    public void setFeatures(WeatherUndergroundResponseFeatures features) {
        this.features = features;
    }

    private String version;
    private String termsofService;
    private WeatherUndergroundResponseFeatures features;
}

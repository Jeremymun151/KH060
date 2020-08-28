package com.example.dto;

import java.util.HashMap;
import java.util.Map;

public class FeatureSearchDTO {

    private String name;
    private Map<String,String> features = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getFeatures() {
        return features;
    }

    public void setFeatures(Map<String, String> features) {
        this.features = features;
    }
}

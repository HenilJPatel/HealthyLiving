package com.example.healthyliving.openfda;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NDCData {
    private Meta meta;
    private List<NDC_Results> results;
    private Map<String, Object> additionalProperties = new LinkedHashMap<>();

    public NDCData(){

    }

    @Override
    public String toString() {
        return "NDCData{" +
                "meta=" + meta +
                ", results=" + results +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    public NDCData(Meta meta, List<NDC_Results> results) {
        super();
        this.meta = meta;
        this.results = results;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<NDC_Results> getResults() {
        return results;
    }

    public void setResults(List<NDC_Results> results) {
        this.results = results;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}

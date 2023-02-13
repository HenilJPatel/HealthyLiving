package com.example.healthyliving.openfda;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LabelData {
    private Meta meta;
    private List<Label_Results> results;
    private Map<String, Object> additionalProperties = new LinkedHashMap<>();

    public LabelData(){

    }

    @Override
    public String toString() {
        return "LabelData{" +
                "meta=" + meta +
                ", results=" + results +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    public LabelData(Meta meta, List<Label_Results> results) {
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

    public List<Label_Results> getResults() {
        return results;
    }

    public void setResults(List<Label_Results> results) {
        this.results = results;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}

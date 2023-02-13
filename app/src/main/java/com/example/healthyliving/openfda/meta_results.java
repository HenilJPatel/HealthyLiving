package com.example.healthyliving.openfda;

import java.util.LinkedHashMap;
import java.util.Map;

public class meta_results {
    private Integer skip;
    private Integer limit;
    private Integer total;
    private Map<String, Object> additionalProperties = new LinkedHashMap<>();

    @Override
    public String toString() {
        return "meta_results{" +
                "skip=" + skip +
                ", limit=" + limit +
                ", total=" + total +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public meta_results() {
    }

    public meta_results(Integer skip, Integer limit, Integer total) {
        this.skip = skip;
        this.limit = limit;
        this.total = total;
    }
}

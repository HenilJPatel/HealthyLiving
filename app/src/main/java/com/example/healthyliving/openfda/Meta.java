package com.example.healthyliving.openfda;

import java.util.LinkedHashMap;
import java.util.Map;

public class Meta {
    private String disclaimer;
    private String terms;
    private String license;
    private String last_updated;
    private meta_results results;
    private Map<String, Object> additionalProperties = new LinkedHashMap<>();

    @Override
    public String toString() {
        return "Meta{" +
                "disclaimer='" + disclaimer + '\'' +
                ", terms='" + terms + '\'' +
                ", license='" + license + '\'' +
                ", last_updated='" + last_updated + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public Meta() {
    }

    public Meta(String disclaimer, String terms, String license, String last_updated, meta_results results) {
        this.disclaimer = disclaimer;
        this.terms = terms;
        this.license = license;
        this.last_updated = last_updated;
        this.results = results;
    }
}

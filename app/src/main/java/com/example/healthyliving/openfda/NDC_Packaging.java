package com.example.healthyliving.openfda;

import java.util.LinkedHashMap;
import java.util.Map;

public class NDC_Packaging {
    private String package_ndc;
    private String description;
    private String marketing_start_date;
    private String marketing_end_date;
    private boolean sample;
    private Map<String, Object> additionalProperties = new LinkedHashMap<>();

    public NDC_Packaging(String package_ndc, String description, String marketing_start_date, String marketing_end_date, boolean sample, Map<String, Object> additionalProperties) {
        this.package_ndc = package_ndc;
        this.description = description;
        this.marketing_start_date = marketing_start_date;
        this.marketing_end_date = marketing_end_date;
        this.sample = sample;
        this.additionalProperties = additionalProperties;
    }

    public NDC_Packaging() {
    }

    @Override
    public String toString() {
        return "NDC_Packaging{" +
                "package_ndc='" + package_ndc + '\'' +
                ", description='" + description + '\'' +
                ", marketing_start_date='" + marketing_start_date + '\'' +
                ", marketing_end_date='" + marketing_end_date + '\'' +
                ", sample=" + sample +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    public String getPackage_ndc() {
        return package_ndc;
    }

    public void setPackage_ndc(String package_ndc) {
        this.package_ndc = package_ndc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMarketing_start_date() {
        return marketing_start_date;
    }

    public void setMarketing_start_date(String marketing_start_date) {
        this.marketing_start_date = marketing_start_date;
    }

    public String getMarketing_end_date() {
        return marketing_end_date;
    }

    public void setMarketing_end_date(String marketing_end_date) {
        this.marketing_end_date = marketing_end_date;
    }

    public boolean isSample() {
        return sample;
    }

    public void setSample(boolean sample) {
        this.sample = sample;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}

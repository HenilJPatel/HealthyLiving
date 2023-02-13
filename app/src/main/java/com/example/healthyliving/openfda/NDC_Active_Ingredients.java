package com.example.healthyliving.openfda;

import java.util.LinkedHashMap;
import java.util.Map;

public class NDC_Active_Ingredients {
    private String name;
    private String strength;
    private Map<String, Object> additionalProperties = new LinkedHashMap<>();

    public NDC_Active_Ingredients() {
    }

    public NDC_Active_Ingredients(String name, String strength, Map<String, Object> additionalProperties) {
        this.name = name;
        this.strength = strength;
        this.additionalProperties = additionalProperties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    @Override
    public String toString() {
        return "NDC_Active_Ingredients{" +
                "name='" + name + '\'' +
                ", strength='" + strength + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}

package com.example.healthyliving.mapclass;

import androidx.annotation.NonNull;

import java.util.LinkedHashMap;
import java.util.Map;

public class Bounds {

    private Northeast northeast;
    private Southwest southwest;
    private Map<String, Object> additionalProperties = new LinkedHashMap<>();

    public Bounds() {
    }

    public Bounds(Northeast northeast, Southwest southwest) {
        super();
        this.northeast = northeast;
        this.southwest = southwest;
    }

    public Northeast getNortheast() {
        return northeast;
    }

    public void setNortheast(Northeast northeast) {
        this.northeast = northeast;
    }

    public Southwest getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Southwest southwest) {
        this.southwest = southwest;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Bounds.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("northeast");
        sb.append('=');
        sb.append(((this.northeast == null)?"<null>":this.northeast));
        sb.append(',');
        sb.append("southwest");
        sb.append('=');
        sb.append(((this.southwest == null)?"<null>":this.southwest));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}

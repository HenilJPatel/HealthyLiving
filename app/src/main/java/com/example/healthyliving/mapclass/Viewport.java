package com.example.healthyliving.mapclass;


import androidx.annotation.NonNull;

import java.util.LinkedHashMap;
import java.util.Map;

public class Viewport {

    private Northeast__1 northeast;
    private Southwest__1 southwest;
    private Map<String, Object> additionalProperties = new LinkedHashMap<>();

    public Viewport() {
    }

    public Viewport(Northeast__1 northeast, Southwest__1 southwest) {
        super();
        this.northeast = northeast;
        this.southwest = southwest;
    }

    public Northeast__1 getNortheast() {
        return northeast;
    }

    public void setNortheast(Northeast__1 northeast) {
        this.northeast = northeast;
    }

    public Southwest__1 getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Southwest__1 southwest) {
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
        sb.append(Viewport.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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

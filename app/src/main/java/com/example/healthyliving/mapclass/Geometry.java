package com.example.healthyliving.mapclass;


import androidx.annotation.NonNull;

import java.util.LinkedHashMap;
import java.util.Map;

public class Geometry {

    private Bounds bounds;
    private Location location;
    private String locationType;
    private Viewport viewport;
    private Map<String, Object> additionalProperties = new LinkedHashMap<>();

    public Geometry() {
    }

    public Geometry(Bounds bounds, Location location, String locationType, Viewport viewport) {
        super();
        this.bounds = bounds;
        this.location = location;
        this.locationType = locationType;
        this.viewport = viewport;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
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
        sb.append(Geometry.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("bounds");
        sb.append('=');
        sb.append(((this.bounds == null)?"<null>":this.bounds));
        sb.append(',');
        sb.append("location");
        sb.append('=');
        sb.append(((this.location == null)?"<null>":this.location));
        sb.append(',');
        sb.append("locationType");
        sb.append('=');
        sb.append(((this.locationType == null)?"<null>":this.locationType));
        sb.append(',');
        sb.append("viewport");
        sb.append('=');
        sb.append(((this.viewport == null)?"<null>":this.viewport));
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

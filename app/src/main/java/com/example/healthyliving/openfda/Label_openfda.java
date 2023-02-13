package com.example.healthyliving.openfda;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Label_openfda {
    private List<Boolean> is_original_packager;
    private List<String> brand_name;
    private List<String> generic_name;
    private List<String> manufacturer_name;
    private List<String> product_ndc;
    private List<String> product_type;
    private List<String> route;
    private List<String> substance_name;
    private List<String> spl_id;
    private List<String> spl_set_id;
    private List<String> package_ndc;
    private List<String> unii;
    private List<String> upc;
    private Map<String, Object> additionalProperties = new LinkedHashMap<>();

    public Label_openfda(List<Boolean> is_original_packager, List<String> brand_name, List<String> generic_name, List<String> manufacturer_name, List<String> product_ndc, List<String> product_type, List<String> route, List<String> substance_name, List<String> spl_id, List<String> spl_set_id, List<String> package_ndc, List<String> unii, List<String> upc) {
        this.is_original_packager = is_original_packager;
        this.brand_name = brand_name;
        this.generic_name = generic_name;
        this.manufacturer_name = manufacturer_name;
        this.product_ndc = product_ndc;
        this.product_type = product_type;
        this.route = route;
        this.substance_name = substance_name;
        this.spl_id = spl_id;
        this.spl_set_id = spl_set_id;
        this.package_ndc = package_ndc;
        this.unii = unii;
        this.upc = upc;
    }

    public Label_openfda() {
    }

    public List<Boolean> isIs_original_packager() {
        return is_original_packager;
    }

    public void setIs_original_packager(List<Boolean> is_original_packager) {
        this.is_original_packager = is_original_packager;
    }

    public List<String> getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(List<String> brand_name) {
        this.brand_name = brand_name;
    }

    public List<String> getGeneric_name() {
        return generic_name;
    }

    public void setGeneric_name(List<String> generic_name) {
        this.generic_name = generic_name;
    }

    public List<String> getManufacturer_name() {
        return manufacturer_name;
    }

    public void setManufacturer_name(List<String> manufacturer_name) {
        this.manufacturer_name = manufacturer_name;
    }

    public List<String> getProduct_ndc() {
        return product_ndc;
    }

    public void setProduct_ndc(List<String> product_ndc) {
        this.product_ndc = product_ndc;
    }

    public List<String> getProduct_type() {
        return product_type;
    }

    public void setProduct_type(List<String> product_type) {
        this.product_type = product_type;
    }

    public List<String> getRoute() {
        return route;
    }

    public void setRoute(List<String> route) {
        this.route = route;
    }

    public List<String> getSubstance_name() {
        return substance_name;
    }

    public void setSubstance_name(List<String> substance_name) {
        this.substance_name = substance_name;
    }

    public List<String> getSpl_id() {
        return spl_id;
    }

    public void setSpl_id(List<String> spl_id) {
        this.spl_id = spl_id;
    }

    public List<String> getSpl_set_id() {
        return spl_set_id;
    }

    public void setSpl_set_id(List<String> spl_set_id) {
        this.spl_set_id = spl_set_id;
    }

    public List<String> getPackage_ndc() {
        return package_ndc;
    }

    public void setPackage_ndc(List<String> package_ndc) {
        this.package_ndc = package_ndc;
    }

    public List<String> getUnii() {
        return unii;
    }

    public void setUnii(List<String> unii) {
        this.unii = unii;
    }

    public List<String> getUpc() {
        return upc;
    }

    public void setUpc(List<String> upc) {
        this.upc = upc;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    @Override
    public String toString() {
        return "Label_openfda{" +
                "is_original_packager=" + is_original_packager +
                ", brand_name=" + brand_name +
                ", generic_name=" + generic_name +
                ", manufacturer_name=" + manufacturer_name +
                ", product_ndc=" + product_ndc +
                ", product_type=" + product_type +
                ", route=" + route +
                ", substance_name=" + substance_name +
                ", spl_id=" + spl_id +
                ", spl_set_id=" + spl_set_id +
                ", package_ndc=" + package_ndc +
                ", unii=" + unii +
                ", upc=" + upc +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}

package com.example.healthyliving.openfda;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NDC_OpenFDA {
    private boolean is_original_packager;
    private List<String> manufacturer_name;
    private List<String> nui;
    private List<String> pharm_class_cs;
    private List<String> pharm_class_epc;
    private List<String> pharm_class_moa;
    private List<String> pharm_class_pe;
    private List<String> rxcui;
    private List<String> spl_set_id;
    private List<String> unii;
    private List<String> upc;
    private Map<String, Object> additionalProperties = new LinkedHashMap<>();

    public NDC_OpenFDA() {
    }

    @Override
    public String toString() {
        return "NDC_OpenFDA{" +
                "is_original_packager=" + is_original_packager +
                ", manufacturer_name=" + manufacturer_name +
                ", nui=" + nui +
                ", pharm_class_cs=" + pharm_class_cs +
                ", pharm_class_epc=" + pharm_class_epc +
                ", pharm_class_moa=" + pharm_class_moa +
                ", pharm_class_pe=" + pharm_class_pe +
                ", rxcui=" + rxcui +
                ", spl_set_id=" + spl_set_id +
                ", unii=" + unii +
                ", upc=" + upc +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    public boolean isIs_original_packager() {
        return is_original_packager;
    }

    public void setIs_original_packager(boolean is_original_packager) {
        this.is_original_packager = is_original_packager;
    }

    public List<String> getManufacturer_name() {
        return manufacturer_name;
    }

    public void setManufacturer_name(List<String> manufacturer_name) {
        this.manufacturer_name = manufacturer_name;
    }

    public List<String> getNui() {
        return nui;
    }

    public void setNui(List<String> nui) {
        this.nui = nui;
    }

    public List<String> getPharm_class_cs() {
        return pharm_class_cs;
    }

    public void setPharm_class_cs(List<String> pharm_class_cs) {
        this.pharm_class_cs = pharm_class_cs;
    }

    public List<String> getPharm_class_epc() {
        return pharm_class_epc;
    }

    public void setPharm_class_epc(List<String> pharm_class_epc) {
        this.pharm_class_epc = pharm_class_epc;
    }

    public List<String> getPharm_class_moa() {
        return pharm_class_moa;
    }

    public void setPharm_class_moa(List<String> pharm_class_moa) {
        this.pharm_class_moa = pharm_class_moa;
    }

    public List<String> getPharm_class_pe() {
        return pharm_class_pe;
    }

    public void setPharm_class_pe(List<String> pharm_class_pe) {
        this.pharm_class_pe = pharm_class_pe;
    }

    public List<String> getRxcui() {
        return rxcui;
    }

    public void setRxcui(List<String> rxcui) {
        this.rxcui = rxcui;
    }

    public List<String> getSpl_set_id() {
        return spl_set_id;
    }

    public void setSpl_set_id(List<String> spl_set_id) {
        this.spl_set_id = spl_set_id;
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

    public NDC_OpenFDA(boolean is_original_packager, List<String> manufacturer_name, List<String> nui, List<String> pharm_class_cs, List<String> pharm_class_epc, List<String> pharm_class_moa, List<String> pharm_class_pe, List<String> rxcui, List<String> spl_set_id, List<String> unii, List<String> upc, Map<String, Object> additionalProperties) {
        this.is_original_packager = is_original_packager;
        this.manufacturer_name = manufacturer_name;
        this.nui = nui;
        this.pharm_class_cs = pharm_class_cs;
        this.pharm_class_epc = pharm_class_epc;
        this.pharm_class_moa = pharm_class_moa;
        this.pharm_class_pe = pharm_class_pe;
        this.rxcui = rxcui;
        this.spl_set_id = spl_set_id;
        this.unii = unii;
        this.upc = upc;
        this.additionalProperties = additionalProperties;
    }

}

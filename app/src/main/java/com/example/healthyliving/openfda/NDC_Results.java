package com.example.healthyliving.openfda;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NDC_Results {
    private String product_id;
    private String product_ndc;
    private String labeler_name;
    private String spl_id;
    private String product_type;
    private boolean finished;
    private String brand_name;
    private String brand_name_base;
    private String brand_name_suffix;
    private String generic_name;
    private String dosage_form;
    private List<String> route;
    private String marketing_start_date;
    private String marketing_end_date;
    private String marketing_category;
    private String application_number;
    private List<String> pharm_class;
    private String dea_schedule;
    private String listing_expiration_date;
    private List<NDC_Active_Ingredients> NDCActive_ingredients;
    private NDC_OpenFDA openFDA;
    private List<NDC_Packaging> NDCPackaging;
    private Map<String, Object> additionalProperties = new LinkedHashMap<>();

    public String getLabeler_name() {
        return labeler_name;
    }

    public void setLabeler_name(String labeler_name) {
        this.labeler_name = labeler_name;
    }

    public NDC_Results() {
    }

    public NDC_Results(String product_id, String product_ndc, String labeler_name, String spl_id, String product_type, boolean finished, String brand_name, String brand_name_base, String brand_name_suffix, String generic_name, String dosage_form, List<String> route, String marketing_start_date, String marketing_end_date, String marketing_category, String application_number, List<String> pharm_class, String dea_schedule, String listing_expiration_date, List<NDC_Active_Ingredients> NDCActive_ingredients, NDC_OpenFDA NDCOpenFDA, List<NDC_Packaging> NDCPackaging) {
        super();
        this.product_id = product_id;
        this.product_ndc = product_ndc;
        this.labeler_name = labeler_name;
        this.spl_id = spl_id;
        this.product_type = product_type;
        this.finished = finished;
        this.brand_name = brand_name;
        this.brand_name_base = brand_name_base;
        this.brand_name_suffix = brand_name_suffix;
        this.generic_name = generic_name;
        this.dosage_form = dosage_form;
        this.route = route;
        this.marketing_start_date = marketing_start_date;
        this.marketing_end_date = marketing_end_date;
        this.marketing_category = marketing_category;
        this.application_number = application_number;
        this.pharm_class = pharm_class;
        this.dea_schedule = dea_schedule;
        this.listing_expiration_date = listing_expiration_date;
        this.NDCActive_ingredients = NDCActive_ingredients;
        this.openFDA = NDCOpenFDA;
        this.NDCPackaging = NDCPackaging;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_ndc() {
        return product_ndc;
    }

    public void setProduct_ndc(String product_ndc) {
        this.product_ndc = product_ndc;
    }

    public String getSpl_id() {
        return spl_id;
    }

    public void setSpl_id(String spl_id) {
        this.spl_id = spl_id;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getBrand_name_base() {
        return brand_name_base;
    }

    public void setBrand_name_base(String brand_name_base) {
        this.brand_name_base = brand_name_base;
    }

    public String getBrand_name_suffix() {
        return brand_name_suffix;
    }

    public void setBrand_name_suffix(String brand_name_suffix) {
        this.brand_name_suffix = brand_name_suffix;
    }

    public String getGeneric_name() {
        return generic_name;
    }

    public void setGeneric_name(String generic_name) {
        this.generic_name = generic_name;
    }

    public String getDosage_form() {
        return dosage_form;
    }

    public void setDosage_form(String dosage_form) {
        this.dosage_form = dosage_form;
    }

    public List<String> getRoute() {
        return route;
    }

    public void setRoute(List<String> route) {
        this.route = route;
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

    public String getMarketing_category() {
        return marketing_category;
    }

    public void setMarketing_category(String marketing_category) {
        this.marketing_category = marketing_category;
    }

    public String getApplication_number() {
        return application_number;
    }

    public void setApplication_number(String application_number) {
        this.application_number = application_number;
    }

    public List<String> getPharm_class() {
        return pharm_class;
    }

    public void setPharm_class(List<String> pharm_class) {
        this.pharm_class = pharm_class;
    }

    public String getDea_schedule() {
        return dea_schedule;
    }

    public void setDea_schedule(String dea_schedule) {
        this.dea_schedule = dea_schedule;
    }

    public String getListing_expiration_date() {
        return listing_expiration_date;
    }

    public void setListing_expiration_date(String listing_expiration_date) {
        this.listing_expiration_date = listing_expiration_date;
    }

    public List<NDC_Active_Ingredients> getActive_ingredients() {
        return NDCActive_ingredients;
    }

    public void setActive_ingredients(List<NDC_Active_Ingredients> NDCActive_ingredients) {
        this.NDCActive_ingredients = NDCActive_ingredients;
    }

    public NDC_OpenFDA getOpenFDA() {
        return openFDA;
    }

    public void setOpenFDA(NDC_OpenFDA NDCOpenFDA) {
        this.openFDA = NDCOpenFDA;
    }

    public List<NDC_Packaging> getPackaging() {
        return NDCPackaging;
    }

    public void setPackaging(List<NDC_Packaging> NDCPackaging) {
        this.NDCPackaging = NDCPackaging;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    @Override
    public String toString() {
        return "NDC_Results{" +
                "product_id='" + product_id + '\'' +
                ", product_ndc='" + product_ndc + '\'' +
                ", spl_id='" + spl_id + '\'' +
                ", product_type='" + product_type + '\'' +
                ", finished=" + finished +
                ", brand_name='" + brand_name + '\'' +
                ", brand_name_base='" + brand_name_base + '\'' +
                ", brand_name_suffix='" + brand_name_suffix + '\'' +
                ", generic_name='" + generic_name + '\'' +
                ", dosage_form='" + dosage_form + '\'' +
                ", route=" + route +
                ", marketing_start_date='" + marketing_start_date + '\'' +
                ", marketing_end_date='" + marketing_end_date + '\'' +
                ", marketing_category='" + marketing_category + '\'' +
                ", application_number='" + application_number + '\'' +
                ", pharm_class=" + pharm_class +
                ", dea_schedule='" + dea_schedule + '\'' +
                ", listing_expiration_date='" + listing_expiration_date + '\'' +
                ", NDCActive_ingredients=" + NDCActive_ingredients +
                ", NDCOpenFDA=" + openFDA +
                ", NDCPackaging=" + NDCPackaging +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}

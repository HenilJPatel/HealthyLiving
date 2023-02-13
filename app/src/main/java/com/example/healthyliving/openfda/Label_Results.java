package com.example.healthyliving.openfda;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Label_Results {
    private String effective_time;
    private List<String> inactive_ingredients;
    private List<String> purpose;
    private List<String> keep_out_of_reach_of_children;
    private List<String> warnings;
    private List<String> questions;
    private List<String> spl_product_data_elements;
    private Label_openfda openfda;
    private String version;
    private List<String> dosage_and_administration;
    private List<String> pregnancy_or_breast_feeding;
    private List<String> stop_use;
    private List<String> storage_and_handling;
    private List<String> do_not_use;
    private List<String> package_label_principal_display_panel;
    private List<String> indications_and_usage;
    private String set_id;
    private String id;
    private List<String> active_ingredients;
    private Map<String, Object> additionalProperties = new LinkedHashMap<>();

    public Label_Results(String effective_time, List<String> inactive_ingredients, List<String> purpose, List<String> keep_out_of_reach_of_children, List<String> warnings, List<String> questions, List<String> spl_product_data_elements, Label_openfda openfda, String version, List<String> dosage_and_administration, List<String> pregnancy_or_breast_feeding, List<String> stop_use, List<String> storage_and_handling, List<String> do_not_use, List<String> package_label_principal_display_panel, List<String> indications_and_usage, String set_id, String id, List<String> active_ingredients) {
        this.effective_time = effective_time;
        this.inactive_ingredients = inactive_ingredients;
        this.purpose = purpose;
        this.keep_out_of_reach_of_children = keep_out_of_reach_of_children;
        this.warnings = warnings;
        this.questions = questions;
        this.spl_product_data_elements = spl_product_data_elements;
        this.openfda = openfda;
        this.version = version;
        this.dosage_and_administration = dosage_and_administration;
        this.pregnancy_or_breast_feeding = pregnancy_or_breast_feeding;
        this.stop_use = stop_use;
        this.storage_and_handling = storage_and_handling;
        this.do_not_use = do_not_use;
        this.package_label_principal_display_panel = package_label_principal_display_panel;
        this.indications_and_usage = indications_and_usage;
        this.set_id = set_id;
        this.id = id;
        this.active_ingredients = active_ingredients;
    }

    @Override
    public String toString() {
        return "Label_Results{" +
                "effective_time='" + effective_time + '\'' +
                ", inactive_ingredients=" + inactive_ingredients +
                ", purpose=" + purpose +
                ", keep_out_of_reach_of_children=" + keep_out_of_reach_of_children +
                ", warnings=" + warnings +
                ", questions=" + questions +
                ", spl_product_data_elements=" + spl_product_data_elements +
                ", openfda=" + openfda +
                ", version='" + version + '\'' +
                ", dosage_and_administration=" + dosage_and_administration +
                ", pregnancy_or_breast_feeding=" + pregnancy_or_breast_feeding +
                ", stop_use=" + stop_use +
                ", storage_and_handling=" + storage_and_handling +
                ", do_not_use=" + do_not_use +
                ", package_label_principal_display_panel=" + package_label_principal_display_panel +
                ", indications_and_usage=" + indications_and_usage +
                ", set_id='" + set_id + '\'' +
                ", id='" + id + '\'' +
                ", active_ingredients=" + active_ingredients +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    public Label_Results() {
    }

    public String getEffective_time() {
        return effective_time;
    }

    public void setEffective_time(String effective_time) {
        this.effective_time = effective_time;
    }

    public List<String> getInactive_ingredients() {
        return inactive_ingredients;
    }

    public void setInactive_ingredients(List<String> inactive_ingredients) {
        this.inactive_ingredients = inactive_ingredients;
    }

    public List<String> getPurpose() {
        return purpose;
    }

    public void setPurpose(List<String> purpose) {
        this.purpose = purpose;
    }

    public List<String> getKeep_out_of_reach_of_children() {
        return keep_out_of_reach_of_children;
    }

    public void setKeep_out_of_reach_of_children(List<String> keep_out_of_reach_of_children) {
        this.keep_out_of_reach_of_children = keep_out_of_reach_of_children;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public List<String> getSpl_product_data_elements() {
        return spl_product_data_elements;
    }

    public void setSpl_product_data_elements(List<String> spl_product_data_elements) {
        this.spl_product_data_elements = spl_product_data_elements;
    }

    public Label_openfda getOpenfda() {
        return openfda;
    }

    public void setOpenfda(Label_openfda openfda) {
        this.openfda = openfda;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getDosage_and_administration() {
        return dosage_and_administration;
    }

    public void setDosage_and_administration(List<String> dosage_and_administration) {
        this.dosage_and_administration = dosage_and_administration;
    }

    public List<String> getPregnancy_or_breast_feeding() {
        return pregnancy_or_breast_feeding;
    }

    public void setPregnancy_or_breast_feeding(List<String> pregnancy_or_breast_feeding) {
        this.pregnancy_or_breast_feeding = pregnancy_or_breast_feeding;
    }

    public List<String> getStop_use() {
        return stop_use;
    }

    public void setStop_use(List<String> stop_use) {
        this.stop_use = stop_use;
    }

    public List<String> getStorage_and_handling() {
        return storage_and_handling;
    }

    public void setStorage_and_handling(List<String> storage_and_handling) {
        this.storage_and_handling = storage_and_handling;
    }

    public List<String> getDo_not_use() {
        return do_not_use;
    }

    public void setDo_not_use(List<String> do_not_use) {
        this.do_not_use = do_not_use;
    }

    public List<String> getPackage_label_principal_display_panel() {
        return package_label_principal_display_panel;
    }

    public void setPackage_label_principal_display_panel(List<String> package_label_principal_display_panel) {
        this.package_label_principal_display_panel = package_label_principal_display_panel;
    }

    public List<String> getIndications_and_usage() {
        return indications_and_usage;
    }

    public void setIndications_and_usage(List<String> indications_and_usage) {
        this.indications_and_usage = indications_and_usage;
    }

    public String getSet_id() {
        return set_id;
    }

    public void setSet_id(String set_id) {
        this.set_id = set_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getActive_ingredients() {
        return active_ingredients;
    }

    public void setActive_ingredients(List<String> active_ingredients) {
        this.active_ingredients = active_ingredients;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}

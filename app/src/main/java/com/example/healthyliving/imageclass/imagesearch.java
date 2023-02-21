package com.example.healthyliving.imageclass;

import java.util.List;

public class imagesearch {
    private String kind;
    private URLClass url;
    private List<ItemsClass> items;

    public List<ItemsClass> getItems() {
        return items;
    }

    public void setItems(List<ItemsClass> items) {
        this.items = items;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public URLClass getUrl() {
        return url;
    }

    public void setUrl(URLClass url) {
        this.url = url;
    }

    public imagesearch() {
    }

    public imagesearch(String kind, URLClass url, List<ItemsClass> items) {
        this.kind = kind;
        this.url = url;
        this.items = items;
    }

    @Override
    public String toString() {
        return "imagesearch{" +
                "kind='" + kind + '\'' +
                ", url=" + url +
                ", items=" + items +
                '}';
    }
}

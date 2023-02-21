package com.example.healthyliving.imageclass;

import java.util.List;

public class PagemapClass {
    private List<CSE> cse_image;

    @Override
    public String toString() {
        return "PagemapClass{" +
                "cse_image=" + cse_image +
                '}';
    }

    public PagemapClass() {
    }

    public PagemapClass(List<CSE> cse_image) {
        this.cse_image = cse_image;
    }

    public List<CSE> getCse_image() {
        return cse_image;
    }

    public void setCse_image(List<CSE> cse_image) {
        this.cse_image = cse_image;
    }
}

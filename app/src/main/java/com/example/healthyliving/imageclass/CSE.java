package com.example.healthyliving.imageclass;

public class CSE {
    private String src;

    public CSE(String src) {
        this.src = src;
    }

    public CSE() {
    }

    @Override
    public String toString() {
        return "CSE{" +
                "src='" + src + '\'' +
                '}';
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}

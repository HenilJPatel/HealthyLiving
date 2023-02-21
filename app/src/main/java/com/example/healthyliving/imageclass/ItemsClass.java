package com.example.healthyliving.imageclass;

public class ItemsClass {
    private PagemapClass pagemap;

    @Override
    public String toString() {
        return "ItemsClass{" +
                "pagemap=" + pagemap +
                '}';
    }

    public PagemapClass getPagemap() {
        return pagemap;
    }

    public void setPagemap(PagemapClass pagemap) {
        this.pagemap = pagemap;
    }

    public ItemsClass(PagemapClass pagemap) {
        this.pagemap = pagemap;
    }

    public ItemsClass() {
    }
}

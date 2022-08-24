package com.example.qualityshield.bean;

public class QualityControlBean {
    private String count;
    private String name;
    private String proportion;

    public QualityControlBean(String count, String name, String proportion) {
        this.count = count;
        this.name = name;
        this.proportion = proportion;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }
}

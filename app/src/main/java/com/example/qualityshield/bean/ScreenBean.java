package com.example.qualityshield.bean;

public class ScreenBean {
    private int id;
    private String cat_name;
    private boolean select;

    public ScreenBean(int id, String cat_name, boolean select) {
        this.id = id;
        this.cat_name = cat_name;
        this.select = select;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}

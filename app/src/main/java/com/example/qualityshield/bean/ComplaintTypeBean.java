package com.example.qualityshield.bean;

/**
 * User: tian
 * Date: 2021/1/14
 * Time: 16:26
 */
public class ComplaintTypeBean {
    private String title;
    private boolean select;

    public ComplaintTypeBean(String title, boolean select) {
        this.title = title;
        this.select = select;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}

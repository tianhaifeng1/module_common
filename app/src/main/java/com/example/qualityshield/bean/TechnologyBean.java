package com.example.qualityshield.bean;

public class TechnologyBean {
    private String name;
    private String value;
    private String content;

    public TechnologyBean(String name, String value, String content) {
        this.name = name;
        this.value = value;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

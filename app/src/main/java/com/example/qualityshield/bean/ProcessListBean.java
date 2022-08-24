package com.example.qualityshield.bean;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ProcessListBean extends BaseExpandNode {
    private String name;
    private List<BaseNode> processChildBeans;

    public List<BaseNode> getProcessChildBeans() {
        return processChildBeans;
    }

    public void setProcessChildBeans(List<BaseNode> processChildBeans) {
        this.processChildBeans = processChildBeans;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return processChildBeans;
    }

    public static class ProcessChildBean extends BaseExpandNode {
        private String name;
        private String value;

        public ProcessChildBean(String name, String value) {
            this.name = name;
            this.value = value;
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

        @Nullable
        @Override
        public List<BaseNode> getChildNode() {
            return null;
        }
    }
}

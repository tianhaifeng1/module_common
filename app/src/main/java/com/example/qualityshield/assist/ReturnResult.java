package com.example.qualityshield.assist;

/**
 * User: tian
 * Date: 2021/1/5
 * Time: 11:43
 */
public class ReturnResult {
    private String status;
    private String msg;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ReturnResult(String status, String msg, String data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}

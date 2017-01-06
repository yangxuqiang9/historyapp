package com.learn.todayinhistroy.http;

import java.util.List;

/**
 * Created by yangxuqiang on 2017/1/3.
 */

public class HttpRequest<T> {
    private String reason;
    private int error_code;
    private List<T> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}

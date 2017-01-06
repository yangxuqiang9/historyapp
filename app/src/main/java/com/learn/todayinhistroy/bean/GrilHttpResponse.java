package com.learn.todayinhistroy.bean;

/**
 * Created by yangxuqiang on 2017/1/3.
 */

public class GrilHttpResponse<T> {
    private boolean error;
    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}

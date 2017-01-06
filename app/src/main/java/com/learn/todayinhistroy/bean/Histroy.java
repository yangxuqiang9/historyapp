package com.learn.todayinhistroy.bean;

import java.util.List;

/**
 * Created by yangxuqiang on 2017/1/3.
 */

public class Histroy<T> {
    private String e_id;
    private String title;
    private String content;
    private String picNo;
    private List<T> picUrl;

    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<T> getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(List<T> picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicNo() {
        return picNo;
    }

    public void setPicNo(String picNo) {
        this.picNo = picNo;
    }
}

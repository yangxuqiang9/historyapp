package com.learn.todayinhistroy.mvp.contact;


/**
 * Created by yangxuqiang on 2017/1/10.
 */

public class DetailContact {
    public interface View{
        void showData(Object o);
        void showFaild(String reason);
    }
    public interface Presenter{
        void getData(String eid);
        void detachView();
    }
}

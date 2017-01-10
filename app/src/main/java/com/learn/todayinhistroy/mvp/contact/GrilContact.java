package com.learn.todayinhistroy.mvp.contact;

/**
 * Created by yangxuqiang on 2017/1/10.
 */

public class GrilContact {
    public interface View{
        void showView(Object o);
        void showFaild(String e);
    }
    public interface presenter{
        void getData(int page,int row);
        void detachView();
    }
}

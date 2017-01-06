package com.learn.todayinhistroy.mvp.contact;

import com.learn.todayinhistroy.bean.TodayInHistoryListEntity;

import java.util.List;

/**
 * Created by yangxuqiang on 2017/1/6.
 */

public class TodayInHistoryContact {
    public interface view{
        void setContent(List<TodayInHistoryListEntity> list);
        void showFaild(String error);
    }
    public interface present{
        void getData(int month,int day);
        void detachView();
    }
}

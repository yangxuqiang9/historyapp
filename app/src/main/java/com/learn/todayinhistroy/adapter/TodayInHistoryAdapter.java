package com.learn.todayinhistroy.adapter;

import android.content.Context;

import com.learn.todayinhistroy.base.BaseAdapter;
import com.learn.todayinhistroy.base.BaseViewHolder;
import com.learn.todayinhistroy.bean.TodayInHistoryListEntity;

import java.util.List;

/**
 * Created by yangxuqiang on 2016/12/27.
 */

public class TodayInHistoryAdapter extends BaseAdapter {
    public TodayInHistoryAdapter(Context context, List<TodayInHistoryListEntity> data, int myLayoutId){
        super(context,data,myLayoutId);
    }
    @Override
    protected void convert(Context context, BaseViewHolder holder, Object o) {
        TodayInHistoryListEntity entity=(TodayInHistoryListEntity)o;
        //TODO
    }
}

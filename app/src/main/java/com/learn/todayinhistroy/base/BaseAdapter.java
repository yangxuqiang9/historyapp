package com.learn.todayinhistroy.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by yangxuqiang on 2016/12/22.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private List<T> data;
    private int myLayoutId;
    private Context context;
    private ItemClickListener listener;
    private LongTouchListener longTouchListener;

    public BaseAdapter(Context context, List<T> data, int myLayoutId){
        this.data=data;
        this.context=context;
        this.myLayoutId=myLayoutId;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(myLayoutId, null);
        return new BaseViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        convert(context,holder,data.get(position));
        if(listener!=null){
            holder.getView(myLayoutId).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClick(v,position);
                }
            });
        }
        if(longTouchListener!=null){
            holder.getView(myLayoutId).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    longTouchListener.touch(v,event,position);
                    return false;
                }
            });
        }
    }

    protected abstract void convert(Context context, BaseViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }

    public interface ItemClickListener{
        public abstract void itemClick(View v,int position);
    }

    public void setOnItemClickListener(ItemClickListener listeren){
        this.listener=listeren;
    }

    public interface LongTouchListener{
        public abstract void touch(View v,MotionEvent event,int position);
    }

    public void setOnLongTouchListener(LongTouchListener touchListener){
        this.longTouchListener=touchListener;
    }

}

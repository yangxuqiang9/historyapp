package com.learn.todayinhistroy.base;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yangxuqiang on 2016/12/22.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    View itemView;
    SparseArray<View> views;

    public BaseViewHolder(View itemView){
        super(itemView);
        this.itemView=itemView;
        views=new SparseArray<>();
    }
    public <T extends View> T getView(int viewId){
        View view = views.get(viewId);
        if(view==null){
            view=itemView.findViewById(viewId);
        }
        return (T) view;
    }

    public BaseViewHolder setText(int viewId,String text){
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }
    public BaseViewHolder setImageDrawable(int viewId, Drawable drawable){
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public BaseViewHolder setOnClickListener(int viewId,View.OnClickListener listener){
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
    public BaseViewHolder setOnItemClickListener(int viewId,View.OnTouchListener listener){
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

}

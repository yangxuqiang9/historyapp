package com.learn.todayinhistroy.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.learn.todayinhistroy.R;
import com.learn.todayinhistroy.base.BaseViewHolder;
import com.learn.todayinhistroy.bean.GrilBean;
import com.learn.todayinhistroy.utils.ImageLoader;
import com.learn.todayinhistroy.utils.ScreenUtils;

import java.util.List;

/**
 * Created by yangxuqiang on 2017/1/10.
 */

public class GrilAdapter extends RecyclerView.Adapter {
    private RecyclerView recyclerView;
    private Context context;
    private List<GrilBean> data;
    private static final int LOADING=0;
    private static final int COMPLETE=1;

    public GrilAdapter(Context context, List data,RecyclerView recyclerView) {
        this.context=context;
        this.data=data;
        this.recyclerView=recyclerView;
        init();
    }

    private void init() {
        GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position==data.size()?2:1;
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //TODO
                Log.e("newstate:" ,newState+"");
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==COMPLETE){
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_gril, null);
            return new BaseViewHolder(inflate);
        }else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_loading, null);
            return new Holder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof BaseViewHolder){
            GrilBean grilBean = data.get(position);
            BaseViewHolder baseholder=(BaseViewHolder)holder;
            ImageView imageView = baseholder.getView(R.id.item_gril);
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.height= ScreenUtils.getScreenHeight(context)/3;
            ImageLoader.load(context,grilBean.getUrl(),imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data!=null? data.size():0;
    }

    @Override
    public int getItemViewType(int position) {

        return position==data.size()?LOADING:COMPLETE;
    }

    public void updata(List<GrilBean> list) {
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder{

        public Holder(View itemView) {
            super(itemView);

        }
    }
}

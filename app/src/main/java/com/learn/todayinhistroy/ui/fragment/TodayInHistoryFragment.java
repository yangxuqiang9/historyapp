package com.learn.todayinhistroy.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.learn.todayinhistroy.R;
import com.learn.todayinhistroy.adapter.TodayInHistoryAdapter;
import com.learn.todayinhistroy.base.BaseAdapter;
import com.learn.todayinhistroy.base.BaseFragment;
import com.learn.todayinhistroy.bean.TodayInHistoryListEntity;
import com.learn.todayinhistroy.mvp.contact.TodayInHistoryContact;
import com.learn.todayinhistroy.mvp.presenter.TodayInHistoryPresenter;
import com.learn.todayinhistroy.ui.activity.DetailActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by yangxuqiang on 2016/12/24.
 */

public class TodayInHistoryFragment extends BaseFragment implements TodayInHistoryContact.view{
    @BindView(R.id.today_floating)
    FloatingActionButton floating;
    @BindView(R.id.today_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.today_swipe)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.today_year)
    TextView timeView;
    private Calendar calendar;
    private List<TodayInHistoryListEntity> data=new ArrayList<>();
    private TodayInHistoryAdapter todayInHistoryAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initEvent() {
        init();
        initTime();
        initRecyclerView();
        addListener();
    }

    private void addListener() {
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new TodayInHistoryPresenter(TodayInHistoryFragment.this).getData(1,1);
            }
        });
        todayInHistoryAdapter.setOnItemClickListener(new BaseAdapter.ItemClickListener() {
            @Override
            public void itemClick(View v, int position) {
                Intent intent = new Intent(context, DetailActivity.class);

                startActivity(intent);
//                context.overridePendingTransition();
            }
        });
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        todayInHistoryAdapter = new TodayInHistoryAdapter(context, data, R.layout.item_todayinhistory);
        recyclerView.setAdapter(todayInHistoryAdapter);
    }

    private void initTime() {
        calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        //TODO
    }

    private void init() {
        swipeRefresh.setColorSchemeColors(Color.RED,Color.GREEN,Color.BLUE);
        swipeRefresh.post(new Runnable() {
            @Override
            public void run() {

            }
        });
//        EventBus.getDefault().register(TodayHistoryEventBus.class);
    }



    @Override
    public void onDestroy() {
//        EventBus.getDefault().unregister(TodayHistoryEventBus.class);
        super.onDestroy();

    }
    @OnClick({R.id.today_floating})
    void OnClick(View v){
        EventBus.getDefault().post(this);
    }


    @Override
    public void setContent(List<TodayInHistoryListEntity> list) {
        todayInHistoryAdapter.updata(list);
        if(swipeRefresh.isRefreshing()){
            swipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public void showFaild(String error) {
        if(swipeRefresh.isRefreshing()){
            swipeRefresh.setRefreshing(false);
        }
    }

    public static class TodayHistoryEventBus{
        @Subscribe(threadMode = ThreadMode.MAIN)
        public void updataMainUI(){
            Toast.makeText(context,"haha",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected int gtLayoutId() {
        return R.layout.fragment_todayinhostory;
    }
}

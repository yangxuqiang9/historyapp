package com.learn.todayinhistroy.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.learn.todayinhistroy.R;
import com.learn.todayinhistroy.adapter.TodayInHistoryAdapter;
import com.learn.todayinhistroy.base.BaseFragment;
import com.learn.todayinhistroy.bean.TodayInHistoryListEntity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

/**
 * Created by yangxuqiang on 2016/12/24.
 */

public class TodayInHistoryFragment extends BaseFragment {
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

    @Override
    protected void initEvent() {
        init();
        initTime();
        initRecyclerView();
        addListener();
    }

    private void addListener() {

    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        todayInHistoryAdapter = new TodayInHistoryAdapter(context, data, R.layout.item_todayinhistory);
        recyclerView.setAdapter(todayInHistoryAdapter);
        TodayInHistoryAdapter todayInHistoryAdapter = new TodayInHistoryAdapter(context, data, R.layout.item_todayinhistory);
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
        EventBus.getDefault().register(this);
    }

    @Override
    protected int gtLayoutId() {
        return R.layout.fragment_todayinhostory;
    }
}

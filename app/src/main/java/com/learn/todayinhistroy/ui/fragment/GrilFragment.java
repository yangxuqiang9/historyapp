package com.learn.todayinhistroy.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.learn.todayinhistroy.R;
import com.learn.todayinhistroy.adapter.GrilAdapter;
import com.learn.todayinhistroy.base.BaseFragment;
import com.learn.todayinhistroy.bean.GrilBean;
import com.learn.todayinhistroy.mvp.contact.GrilContact;
import com.learn.todayinhistroy.mvp.presenter.GrilPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by yangxuqiang on 2016/12/24.
 */

public class GrilFragment extends BaseFragment implements GrilContact.View {
    @BindView(R.id.gril_float)
    FloatingActionButton floating;
    @BindView(R.id.gril_recycle)
    RecyclerView recyclerView;
    @BindView(R.id.gril_swiperefresh)
    SwipeRefreshLayout swipeRefresh;
    private List data=new ArrayList();
    private GrilAdapter grilAdapter;
    private GrilPresenter grilPresenter;

    @Override
    protected void initEvent() {
        swipeRefresh.setColorSchemeColors(Color.RED,Color.BLACK,Color.BLUE);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                grilPresenter.getData(1,16);
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        grilAdapter = new GrilAdapter(context, data,recyclerView);
        recyclerView.setAdapter(grilAdapter);

        grilPresenter = new GrilPresenter(this);
        grilPresenter.getData(1,16);
    }

    @Override
    protected int gtLayoutId() {
        return R.layout.fragment_gril;
    }

    @Override
    public void showView(Object o) {
        List<GrilBean> list= (List<GrilBean>) o;
        grilAdapter.updata(list);

        if(swipeRefresh!=null&&swipeRefresh.isRefreshing())
            swipeRefresh.setRefreshing(false);
    }

    @Override
    public void loadMore(Object p) {
        List<GrilBean> more=(List<GrilBean>) p;
        grilAdapter.notifyItemRangeInserted(data.size(),data.size()+more.size());
        data.addAll(more);
    }

    @Override
    public void showFaild(String e) {
        if(swipeRefresh!=null&&swipeRefresh.isRefreshing())
            swipeRefresh.setRefreshing(false);
        Toast.makeText(context,e,Toast.LENGTH_LONG).show();
    }
}

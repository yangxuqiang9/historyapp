package com.learn.todayinhistroy.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.learn.todayinhistroy.R;
import com.learn.todayinhistroy.base.BaseActivity;
import com.learn.todayinhistroy.bean.Histroy;
import com.learn.todayinhistroy.bean.Picture;
import com.learn.todayinhistroy.mvp.contact.DetailContact;
import com.learn.todayinhistroy.mvp.presenter.DetailPresenter;

import butterknife.BindView;

/**
 * Created by yangxuqiang on 2017/1/7.
 */

public class DetailActivity extends BaseActivity implements DetailContact.View {
    @BindView(R.id.detail_appbar)
    AppBarLayout appBar;
    @BindView(R.id.detail_collpse)
    CollapsingToolbarLayout collpse;
    @BindView(R.id.detail_content)
    RelativeLayout content;
    @BindView(R.id.detail_title)
    TextView title;
    @BindView(R.id.detial_floating)
    FloatingActionButton floating;
    @BindView(R.id.derail_toolbar)
    Toolbar toolbar;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

    }

    private void init() {
        DetailPresenter detailPresenter = new DetailPresenter(this);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String date=intent.getStringExtra("date");
        detailPresenter.getData(id);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void showData(Object o) {
        Histroy<Picture> data= (Histroy<Picture>) o;
        toolbar.setTitle(data.getTitle());
        title.setText(data.getContent());
    }

    @Override
    public void showFaild(String reason) {
        Toast.makeText(this,reason,Toast.LENGTH_LONG).show();
    }
}

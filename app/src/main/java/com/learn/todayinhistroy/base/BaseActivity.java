package com.learn.todayinhistroy.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by yangxuqiang on 2016/12/22.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected BaseActivity mContext;
    private List<Activity> mActivitys=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        this.mContext=this;
        mActivitys.add(this);
        ButterKnife.bind(this);
    }

    protected abstract int getLayoutId();

    @Override
    public void onClick(View v) {

    }

    protected void finishAll(){
        for(Activity a:mActivitys){
            a.finish();
        }
    }
    protected void setToolbar(Toolbar toolbar,String title){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

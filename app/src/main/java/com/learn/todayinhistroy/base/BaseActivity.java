package com.learn.todayinhistroy.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.umeng.message.PushAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by yangxuqiang on 2016/12/22.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected BaseActivity mContext;
    private List<Activity> mActivitys=new ArrayList<>();
    public BackButtonListener backButtonListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        this.mContext=this;
        mActivitys.add(this);
        ButterKnife.bind(this);
        //启动友盟推送
        PushAgent.getInstance(this).onAppStart();
        BaseApplication application = (BaseApplication) getApplication();
        String registrationId = application.mPushAgent.getRegistrationId();
        Log.d("tokenid",registrationId);
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

    @Override
    public void onBackPressed() {
        if(backButtonListener!=null){
            backButtonListener.onClick(null);
        }
    }
    public void setBackButtonListener(BackButtonListener backButtonListener){
        this.backButtonListener=backButtonListener;
    }
    public interface BackButtonListener{
        void onClick(View view);
    }
}

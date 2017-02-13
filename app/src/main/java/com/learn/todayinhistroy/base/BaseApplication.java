package com.learn.todayinhistroy.base;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

/**
 * Created by yangxuqiang on 2016/12/22.
 */

public class BaseApplication extends MultiDexApplication {
    public static Context overallContext;
    private static BaseApplication application;
    public PushAgent mPushAgent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.overallContext=getApplicationContext();
        this.application=this;
        printAppInfo();
        //注册友盟推送
        registUmeng();
    }

    private void registUmeng() {
        mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.d("注册成功","devicetoken:"+deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.d("注册失败","原因"+s+"****"+s1);
            }
        });
    }

    public static BaseApplication getInstance(){
        return application;
    }

    /**
     * 打印手机信息
     */
    private void printAppInfo() {
        Log.d("OS:", Build.VERSION.RELEASE+"("+Build.HOST+":"+Build.VERSION.SDK_INT+")");
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

    }
}

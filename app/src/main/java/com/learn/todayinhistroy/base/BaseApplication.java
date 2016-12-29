package com.learn.todayinhistroy.base;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.util.Log;

/**
 * Created by yangxuqiang on 2016/12/22.
 */

public class BaseApplication extends Application {
    public static Context overallContext;
    private static BaseApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        this.overallContext=getApplicationContext();
        this.application=this;
        printAppInfo();
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

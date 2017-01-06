package com.learn.todayinhistroy.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.learn.todayinhistroy.base.BaseApplication;

/**
 * Created by yangxuqiang on 2017/1/3.
 */

public class Tools {
    public static boolean checkNetConnected(){
        ConnectivityManager networkService = (ConnectivityManager) BaseApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = networkService.getActiveNetworkInfo();
        return networkService.getActiveNetworkInfo()!=null;
    }
}

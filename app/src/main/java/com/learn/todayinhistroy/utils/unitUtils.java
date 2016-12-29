package com.learn.todayinhistroy.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by yangxuqiang on 2016/12/27.
 */

public class unitUtils {
    public static int dp2px(Context context,int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources().getDisplayMetrics());
    }
}

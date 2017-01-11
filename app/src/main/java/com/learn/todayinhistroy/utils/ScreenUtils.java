package com.learn.todayinhistroy.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by yangxuqiang on 2017/1/10.
 */

public class ScreenUtils {
    public static int getScreenWidth(Context context) {
        WindowManager service = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        service.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
    public static int getScreenHeight(Context context) {
        WindowManager service = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        service.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }
    public static Bitmap snapShotWithoutStatusBar(Activity activity){
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap drawingCache = decorView.getDrawingCache();
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int top = rect.top;
        int width = rect.width();
        int height = rect.height();
        Bitmap bitmap = Bitmap.createBitmap(drawingCache, 0, top, width, height - top);
        decorView.destroyDrawingCache();
        return bitmap;
    }
}

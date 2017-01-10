package com.learn.todayinhistroy.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.util.Util;
import com.learn.todayinhistroy.R;

/**
 * Created by yangxuqiang on 2017/1/10.
 */

public class ImageLoader {
    public static void load(Context context, String url, ImageView imageView){
        if(!TextUtils.isEmpty(url))
            Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }
    public static void load(Context context, String url, SimpleTarget target){
        if(!TextUtils.isEmpty(url))
            Glide.with(context).load(url).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).into(target);
    }
    public static void load(Context context,ImageView imageView){
        Glide.with(context).load(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into(imageView);
    }
}

package com.learn.todayinhistroy.http;

import com.learn.todayinhistroy.base.BaseApplication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by yangxuqiang on 2017/1/3.
 */

public class OkhttpClientHelper {

    private static OkhttpClientHelper okhttpClientHelper;
    private static Interceptor interceptor=new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response proceed = chain.proceed(chain.request());
            return proceed.newBuilder().removeHeader("Pragma").removeHeader("Cache-Control").header("Cache-Control","public,max-age="+(60*10)).build();
        }
    };

    public OkhttpClientHelper() {

    }
    public static OkhttpClientHelper getIntance(){
        if (okhttpClientHelper==null) {
            synchronized (OkhttpClientHelper.class) {
                if(okhttpClientHelper==null)
                    okhttpClientHelper = new OkhttpClientHelper();
            }
        }
        return okhttpClientHelper;
    }
    public OkHttpClient getOkHttpClint(){
        File http_cache = new File(BaseApplication.getInstance().getCacheDir(), "http_cache");
        if(!http_cache.exists()){
            http_cache.mkdirs();
        }
        Cache cache = new Cache(http_cache, 1024 * 1024 * 2);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(1000 * 5, TimeUnit.SECONDS).writeTimeout(1000 * 5, TimeUnit.SECONDS)
                .connectTimeout(1000 * 5, TimeUnit.SECONDS).addNetworkInterceptor(interceptor).cache(cache).build();
        return okHttpClient;
    }

}

package com.learn.todayinhistroy.http;

import com.learn.todayinhistroy.bean.GrilBean;
import com.learn.todayinhistroy.bean.GrilHttpResponse;
import com.learn.todayinhistroy.bean.Histroy;
import com.learn.todayinhistroy.bean.Picture;
import com.learn.todayinhistroy.bean.TodayInHistoryListEntity;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by yangxuqiang on 2017/1/3.
 */

public class RetrofitHelper {

    private static RetrofitHelper retrofitHelper;
    private final RequestService grilService;
    private final RequestService requestService;

    public RetrofitHelper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://v.juhe.cn/todayOnhistory/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(OkhttpClientHelper.getIntance().getOkHttpClint())
                .build();
        requestService = retrofit.create(RequestService.class);
        Retrofit grilRetrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(OkhttpClientHelper.getIntance().getOkHttpClint())
                .build();
        grilService = grilRetrofit.create(RequestService.class);
    }
    public static RetrofitHelper getIntance(){
        if (retrofitHelper==null) {
            synchronized (RetrofitHelper.class) {
                if (retrofitHelper==null) {
                    retrofitHelper = new RetrofitHelper();
                }
            }
        }
        return retrofitHelper;
    }

    public Observable<HttpRequest<TodayInHistoryListEntity>> getHistoryList(int month,int eid){
        Observable<HttpRequest<TodayInHistoryListEntity>> request = requestService.request("69a7eeba7869f8bdcdee7b2bc3bb5aa2", month+"/"+eid);
        return request;
    }
    public Observable<HttpRequest<Histroy<Picture>>> getHistoryDetial(String eid){
        return requestService.getHistoryDetail("69a7eeba7869f8bdcdee7b2bc3bb5aa2",eid);
    }
    public Observable<GrilHttpResponse<List<GrilBean>>> getGrilList(int page,int num){
        return grilService.getGrilList(num,page);
    }
}

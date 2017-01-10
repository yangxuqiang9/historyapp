package com.learn.todayinhistroy.http;

import com.learn.todayinhistroy.bean.GrilBean;
import com.learn.todayinhistroy.bean.GrilHttpResponse;
import com.learn.todayinhistroy.bean.Histroy;
import com.learn.todayinhistroy.bean.Picture;
import com.learn.todayinhistroy.bean.TodayInHistoryListEntity;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by yangxuqiang on 2016/12/29.
 */

public interface RequestService {

    /**
     * 获取历史列表
     * @return
     */
    @FormUrlEncoded
    @POST("queryEvent.php")
    Observable<HttpRequest<TodayInHistoryListEntity>> request(@Field("key") String key,@Field("date") String date);

    @FormUrlEncoded
    @POST("queryDetail.php")
    Observable<HttpRequest<Histroy<Picture>>> getHistoryDetail(@Field("Key") String key,@Field("e_id") String e_id);

    @GET("data/福利/{num}/{page}")
    Observable<GrilHttpResponse<List<GrilBean>>> getGrilList(@Path("num") int num, @Path("page") int page);
}

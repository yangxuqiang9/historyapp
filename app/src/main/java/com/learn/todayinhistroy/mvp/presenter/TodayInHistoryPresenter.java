package com.learn.todayinhistroy.mvp.presenter;

import com.learn.todayinhistroy.bean.TodayInHistoryListEntity;
import com.learn.todayinhistroy.http.HttpRequest;
import com.learn.todayinhistroy.http.RetrofitHelper;
import com.learn.todayinhistroy.mvp.contact.TodayInHistoryContact;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yangxuqiang on 2017/1/6.
 */

public class TodayInHistoryPresenter implements TodayInHistoryContact.present {

    private TodayInHistoryContact.view view;

    public TodayInHistoryPresenter(TodayInHistoryContact.view view) {
        this.view=view;
    }

    @Override
    public void getData(int month, int day) {
        RetrofitHelper.getIntance().getHistoryList(month,day).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpRequest<TodayInHistoryListEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showFaild(e.toString());
                    }

                    @Override
                    public void onNext(HttpRequest<TodayInHistoryListEntity> todayInHistoryListEntityHttpRequest) {
                        if(view!=null){
                            if(todayInHistoryListEntityHttpRequest.getError_code()==0){
                                view.setContent(todayInHistoryListEntityHttpRequest.getResult());
                            }else{
                                view.showFaild(todayInHistoryListEntityHttpRequest.getReason());
                            }
                        }
                    }
                });
    }

    @Override
    public void detachView() {
        view=null;
    }

}

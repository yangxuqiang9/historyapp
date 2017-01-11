package com.learn.todayinhistroy.mvp.presenter;

import com.learn.todayinhistroy.bean.GrilBean;
import com.learn.todayinhistroy.bean.GrilHttpResponse;
import com.learn.todayinhistroy.http.RetrofitHelper;
import com.learn.todayinhistroy.mvp.contact.GrilContact;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yangxuqiang on 2017/1/10.
 */

public class GrilPresenter implements GrilContact.presenter {
    private GrilContact.View view;

    public GrilPresenter(GrilContact.View view) {
        this.view=view;
    }

    @Override
    public void getData(int page,int row) {
        RetrofitHelper.getIntance().getGrilList(page,row).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GrilHttpResponse<List<GrilBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showFaild(e.getMessage());
                    }

                    @Override
                    public void onNext(GrilHttpResponse<List<GrilBean>> listGrilHttpResponse) {
                        List<GrilBean> results = listGrilHttpResponse.getResults();
                        view.showView(results);
                    }
                });
    }

    @Override
    public void loadMore(int page) {
        RetrofitHelper.getIntance().getGrilList(page,16).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GrilHttpResponse<List<GrilBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showFaild(e.getMessage());
                    }

                    @Override
                    public void onNext(GrilHttpResponse<List<GrilBean>> listGrilHttpResponse) {
                        view.loadMore(listGrilHttpResponse.getResults());
                    }
                });
    }

    @Override
    public void detachView() {
        view=null;
    }
}

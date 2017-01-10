package com.learn.todayinhistroy.mvp.presenter;

import com.learn.todayinhistroy.bean.Histroy;
import com.learn.todayinhistroy.bean.Picture;
import com.learn.todayinhistroy.http.HttpRequest;
import com.learn.todayinhistroy.http.RetrofitHelper;
import com.learn.todayinhistroy.mvp.contact.DetailContact;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yangxuqiang on 2017/1/10.
 */

public class DetailPresenter implements DetailContact.Presenter{
    private DetailContact.View view;

    public DetailPresenter(DetailContact.View view) {
        this.view=view;
    }

    @Override
    public void getData(String eid) {
        RetrofitHelper.getIntance().getHistoryDetial(eid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HttpRequest<Histroy<Picture>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showFaild(e.getMessage());
                    }

                    @Override
                    public void onNext(HttpRequest<Histroy<Picture>> histroyHttpRequest) {
                        if(view!=null){
                            if(histroyHttpRequest.getError_code()==0){
                                List<Histroy<Picture>> result = histroyHttpRequest.getResult();
                                if(result!=null&&result.size()!=0){
                                    view.showData(result.get(0));
                                }
                            }else {
                                view.showFaild(histroyHttpRequest.getReason());
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

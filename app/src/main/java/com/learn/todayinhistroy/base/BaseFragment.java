package com.learn.todayinhistroy.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by yangxuqiang on 2016/12/24.
 */

public abstract class BaseFragment extends Fragment {
    protected Activity context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(gtLayoutId(), container, false);
        ButterKnife.bind(this,view);
        this.context=getActivity();
        initEvent();
        return view;
    }

    protected abstract void initEvent();

    protected abstract int gtLayoutId();
}

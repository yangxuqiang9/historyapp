package com.learn.todayinhistroy.ui.fragment;

import android.webkit.WebSettings;
import android.webkit.WebView;

import com.learn.todayinhistroy.R;
import com.learn.todayinhistroy.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by yangxuqiang on 2016/12/24.
 */

public class LikeFragment extends BaseFragment {
    @BindView(R.id.webview)
    WebView webView;

    @Override
    protected void initEvent() {
        initWebView();
    }

    private void initWebView() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl("http://www.baidu.com/");

    }

    @Override
    protected int gtLayoutId() {
        return R.layout.fragment_like;
    }
}

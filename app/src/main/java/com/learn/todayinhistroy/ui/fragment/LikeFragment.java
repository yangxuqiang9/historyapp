package com.learn.todayinhistroy.ui.fragment;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.learn.todayinhistroy.R;
import com.learn.todayinhistroy.base.BaseActivity;
import com.learn.todayinhistroy.base.BaseFragment;
import com.learn.todayinhistroy.ui.activity.MainActivity;

import butterknife.BindView;

/**
 * Created by yangxuqiang on 2016/12/24.
 */

public class LikeFragment extends BaseFragment {
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void initEvent() {
        initWebView();
    }

    private void initWebView() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl("http://www.baidu.com/");
        webView.setWebViewClient(new LikeWebViewClient());
        MainActivity main=(MainActivity)context;
        main.setBackButtonListener(new BaseActivity.BackButtonListener() {
            @Override
            public void onClick(View view) {
                webView.goBack();
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload();
            }
        });
        refreshLayout.setRefreshing(true);
    }

    @Override
    protected int gtLayoutId() {
        return R.layout.fragment_like;
    }

    class LikeWebViewClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if(refreshLayout!=null&&refreshLayout.isRefreshing()){
                refreshLayout.setRefreshing(false);
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            webView.loadUrl("http://www.baidu.com");
            return true;
        }
    }
}

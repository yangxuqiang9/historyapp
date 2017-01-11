package com.learn.todayinhistroy.ui.fragment;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.learn.todayinhistroy.R;
import com.learn.todayinhistroy.base.BaseFragment;
import com.learn.todayinhistroy.mvp.contact.GrilContact;
import com.learn.todayinhistroy.utils.ScreenUtils;

import butterknife.BindView;

/**
 * Created by yangxuqiang on 2016/12/24.
 */

public class AboutFragment extends BaseFragment {
    @BindView(R.id.imageview)
    ImageView imageview;
    @BindView(R.id.button)
    Button button;
    @Override
    protected void initEvent() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = ScreenUtils.snapShotWithoutStatusBar(context);
                imageview.setImageBitmap(bitmap);
            }
        });
    }

    public void click(View v){

    }

    @Override
    protected int gtLayoutId() {
        return R.layout.fragment_about;
    }
}

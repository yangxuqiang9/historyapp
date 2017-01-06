package com.learn.todayinhistroy.ui.activity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.learn.todayinhistroy.R;
import com.learn.todayinhistroy.base.BaseActivity;
import com.learn.todayinhistroy.base.BaseFragment;
import com.learn.todayinhistroy.ui.fragment.AboutFragment;
import com.learn.todayinhistroy.ui.fragment.GrilFragment;
import com.learn.todayinhistroy.ui.fragment.LikeFragment;
import com.learn.todayinhistroy.ui.fragment.TodayInHistoryFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_drawerlayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_framelayout)
    FrameLayout frameLayout;
    @BindView(R.id.main_navigation)
    NavigationView navagtion;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private MenuItem currentItem;
    private Fragment currentFragment;
    private GrilFragment grilFragment;
    private AboutFragment aboutFragment;
    private LikeFragment likeFragment;
    private TodayInHistoryFragment todayInHistoryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        initData();
        addListener();
    }

    private void addListener() {
        /**菜单按钮监听*/
        navagtion.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N_MR1)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId){
                    case R.id.drawer_todayinhository:
                        switchFragment(todayInHistoryFragment);
                        currentFragment=todayInHistoryFragment;
                        setTitle("时间长河");
                        break;
                    case R.id.drawer_gril:
                        switchFragment(grilFragment);
                        currentFragment=grilFragment;
                        setTitle("图片");
                        break;
                    case R.id.drawer_like:
                        switchFragment(likeFragment);
                        currentFragment=likeFragment;
                        setTitle("收藏");
                        break;
                    case R.id.drawer_about:
                        switchFragment(aboutFragment);
                        currentFragment=aboutFragment;
                        setTitle("关于");
                        break;
                    default:

                }
                drawerLayout.closeDrawer(navagtion);
                return true;
            }
        });
    }

    private void setTitle(String title){
        toolbar.setTitle(title);
    }
    private void switchFragment(BaseFragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(fragment.isAdded()){
            fragmentTransaction.hide(currentFragment).show(fragment).commit();
        }else {
            fragmentTransaction.hide(currentFragment).add(R.id.main_framelayout,fragment).commit();
        }
    }
    private void initData() {
        todayInHistoryFragment = new TodayInHistoryFragment();
        grilFragment = new GrilFragment();
        aboutFragment = new AboutFragment();
        likeFragment = new LikeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_framelayout, todayInHistoryFragment).commit();
        currentItem = navagtion.getMenu().findItem(R.id.drawer_todayinhository);
        currentFragment= todayInHistoryFragment;
        currentItem.setCheckable(true);
    }

    private void initUI() {
        toolbar.setTitle("时间的长河");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        actionBarDrawerToggle = new ActionBarDrawerToggle(mContext, drawerLayout, toolbar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        if(navagtion.isShown()){
            drawerLayout.closeDrawers();
        }else {
            showExit();
        }
    }

    private void showExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("信息").setMessage("退出").setPositiveButton("确定",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finishAll();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().setCanceledOnTouchOutside(false);
        builder.show();
    }
}

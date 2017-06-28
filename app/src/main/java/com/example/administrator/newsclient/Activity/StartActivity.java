package com.example.administrator.newsclient.Activity;

import android.content.Intent;
import android.os.SystemClock;

import com.example.administrator.newsclient.R;
import com.example.administrator.newsclient.Util.SharedPrefUtil;

public class StartActivity extends BaseActivity{


    @Override
    protected int getLayout() {
        return R.layout.activity_start;
    }

    @Override
    public void initData() {
        new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(2000);

                // 读取不到key为firstRun的值，则默认返回true，表示第一次启动应用
                boolean firstRun = SharedPrefUtil.getBoolean(
                        getApplicationContext(), "firstRun", true);
                if (firstRun) {
                    SharedPrefUtil.saveBoolean(StartActivity.this,
                            "firstRun", false);
                    enterGuideActivity();
                } else {
                    enterMainActivity();
                }
            }
        }.start();

    }

    private void enterMainActivity() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    private void enterGuideActivity() {
        startActivity(new Intent(this,GuideActivity.class));
        finish();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initView() {

    }
}

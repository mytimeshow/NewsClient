package com.example.administrator.newsclient.Activity;

import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.administrator.newsclient.Bean.NewsTopbean;
import com.example.administrator.newsclient.R;

public class NewsDetailActivity extends BaseActivity {
private ProgressBar mProgressBar;
    private WebView mWebView;

    @Override
    protected int getLayout() {
        return R.layout.activity_news_detail;
    }

    @Override
    public void initData() {
        NewsTopbean.ResultBean resultbean= (NewsTopbean.ResultBean) getIntent().getSerializableExtra("url");
        mWebView.loadUrl(resultbean.getUrl());
        // 显示标题栏左上角的返回图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 显示标题栏
        getSupportActionBar().setTitle(resultbean.getTitle());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();   // 标题栏左上角的返回按钮，退出当前界面
            return true;
        }


        return super.onOptionsItemSelected(item);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initView() {
        mProgressBar= (ProgressBar) findViewById(R.id.progressbar);
        initWebview();

    }

    private void initWebview() {
        mWebView= (WebView) findViewById(R.id.webview);
        // 当点击WebView显示的网页的链接时，禁止使用其它浏览器打开
        mWebView.setWebViewClient(new WebViewClient());
        //设置webview支持javascript脚本
        mWebView.getSettings().setJavaScriptEnabled(true);
        // 当WebView加载网页时，显示加载网页的进度
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {       // 加载完成，隐藏进度条
                    mProgressBar.setVisibility(View.GONE);
                } else {                        // 显示加载进度
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                    System.out.println("---------percent: " + newProgress);
                }
            }
            });

        }



}

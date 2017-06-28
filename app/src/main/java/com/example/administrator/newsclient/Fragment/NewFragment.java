package com.example.administrator.newsclient.Fragment;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.administrator.newsclient.Activity.NewsDetailActivity;
import com.example.administrator.newsclient.Adapter.NewsAdapter;
import com.example.administrator.newsclient.Bean.NewsTopbean;
import com.example.administrator.newsclient.R;
import com.example.administrator.newsclient.UrlManage.UrlManage;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;


/**
 * Created by yls on 2017/6/27.
 */

public class NewFragment extends BaseFragment {
    private ListView listView;
    private SpringView spring_view;
    /** 新闻类别id */
    private String channelId;

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_new;
    }

    @Override
    public void initView() {

        listView= (ListView) mView.findViewById(R.id.list_view);
        initspring_view();


    }

    private void initspring_view() {
        spring_view= (SpringView) mView.findViewById(R.id.spring_view);
        spring_view.setHeader(new DefaultHeader(getContext()));
        spring_view.setFooter(new DefaultFooter(getContext()));
        spring_view.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {

                //http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
                String url = UrlManage.getUrl(channelId);

                HttpUtils utils = new HttpUtils();
                utils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String json = responseInfo.result;
                        System.out.println("----服务器返回的.json数据:" + json);

                        json =json.replace(channelId, "result");
                        Gson gson = new Gson();
                        NewsTopbean newsDatas = gson.fromJson(json, NewsTopbean.class);
                        System.out.println("----解析json:" + newsDatas.getResult().size());

                        //显示数据到列表中
                        showDatas(newsDatas);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        error.printStackTrace();
                    }
                });







                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        spring_view.onFinishFreshAndLoad();
                    }
                },2000 );
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        spring_view.onFinishFreshAndLoad();
                    }
                },2000 );
            }
        });
    }

    @Override
    public void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    NewsTopbean.ResultBean   result1= (NewsTopbean.ResultBean) parent.getItemAtPosition(position);
                Intent intent=new Intent();
                intent.setClass(mActivity, NewsDetailActivity.class);
                intent.putExtra("url", result1);
                startActivity(intent);
            }
        });

    }

    @Override
    public void initData() {
        getDatafromServer();
    }

    private void getDatafromServer() {
        // http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
        String url = UrlManage.getUrl(channelId);

        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String json = responseInfo.result;
                System.out.println("----服务器返回的.json数据:" + json);

                json =json.replace(channelId, "result");
                Gson gson = new Gson();
                NewsTopbean newsDatas = gson.fromJson(json, NewsTopbean.class);
                System.out.println("----解析json:" + newsDatas.getResult().size());

                 //显示数据到列表中
                 showDatas(newsDatas);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                error.printStackTrace();
            }
        });



//        //请求网络数据..
//        String url=UrlManage.getUrl(channelId);
//        OkHttpClient client=new OkHttpClient();
//        Request request=new Request.Builder().url(url).build();
//        Call call=client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String json=;
//                json=json.replace(channelId,"Result");
//                Gson gson=new Gson();
//
//                NewsTopbean newbean=gson.fromJson(json,NewsTopbean.class);
//                int c=newbean.getResult().size();
//                System.out.print(c);
//            }
//        });

    }

    private void showDatas(NewsTopbean newsDatas) {
        if (newsDatas == null
                || newsDatas.getResult() == null
                || newsDatas.getResult().size() == 0) {
            System.out.println("----没有获取到服务器的新闻数据");
            return;
        }

        // （1）显示轮播图
        List<NewsTopbean.ResultBean.AdsBean> ads
                = newsDatas.getResult().get(0).getAds();
if(ads!=null && ads.size()>0){

    View headerView=View.inflate(mActivity,R.layout.list_header,null);
    SliderLayout slederlayout= (SliderLayout) headerView.findViewById(R.id.slider_layout);
    for(int i=0;i<ads.size();i++){
        NewsTopbean.ResultBean.AdsBean adsbean=ads.get(i);
        TextSliderView tsv=new TextSliderView(mActivity);
        tsv.description(adsbean.getTitle()).image(adsbean.getImgsrc());
        // 添加子界面
        slederlayout.addSlider(tsv);
        // 设置点击事件..
        tsv.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                showToast(slider.getDescription());
            }
        });

    }
listView.addHeaderView(headerView);
}



                // （2）显示新闻列表
                NewsAdapter newsAdapter = new NewsAdapter(
                mActivity, newsDatas.getResult());
        listView.setAdapter(newsAdapter);
        newsAdapter.notifyDataSetChanged();
    }
}

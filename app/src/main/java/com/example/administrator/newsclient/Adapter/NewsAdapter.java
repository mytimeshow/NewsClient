package com.example.administrator.newsclient.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.newsclient.Bean.NewsTopbean;
import com.example.administrator.newsclient.R;

import java.util.List;

/**
 * Created by yls on 2017/6/28.
 */

public class NewsAdapter extends BaseAdapter{
    private Context context;

    /** 列表显示的新闻数据 */
    private List<NewsTopbean.ResultBean> listDatas;
    public NewsAdapter(Context context, List<NewsTopbean.ResultBean> listDatas){
        this.context=context;
        this.listDatas=listDatas;

    }



    @Override
    public int getCount() {
        return listDatas.size();
    }

    @Override
    public NewsTopbean.ResultBean getItem(int position) {
        return listDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 列表项数据
        NewsTopbean.ResultBean info = (NewsTopbean.ResultBean) getItem(position);

        int itemViewType = getItemViewType(position);
        if (itemViewType == ITEM_TYPE_WITH_1_IMAGE) {   // 第一种类型的列表项

            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_news_1, null);
            }

            // 查找列表item中的子控件
            ImageView ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            TextView tvSource = (TextView) convertView.findViewById(R.id.tv_source);
            TextView tvComment = (TextView) convertView.findViewById(R.id.tv_comment);

            // 显示列表item中的子控件
            tvTitle.setText(info.getTitle());
            tvSource.setText(info.getSource());
            tvComment.setText(info.getReplyCount() + "跟帖");
            Glide.with(context).load(info.getImgsrc()).into(ivIcon);

        } else if (itemViewType == ITEM_TYPE_WITH_3_IMAGE) {    // 第二种类型的列表项

            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_news_2, null);
            }

            // 查找列表item中的子控件
            TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            TextView  tvComment = (TextView) convertView.findViewById(R.id.tv_comment);
            ImageView iv01 = (ImageView) convertView.findViewById(R.id.iv_01);
            ImageView iv02 = (ImageView) convertView.findViewById(R.id.iv_02);
            ImageView iv03 = (ImageView) convertView.findViewById(R.id.iv_03);

            // 显示列表item中的子控件
            tvTitle.setText(info.getTitle());
            tvComment.setText(info.getReplyCount() + "跟帖");
            try {
                Glide.with(context).load(info.getImgsrc()).into(iv01);
                Glide.with(context).load(info.getImgextra().get(0).getImgsrc())
                        .into(iv02);
                Glide.with(context).load(info.getImgextra().get(1).getImgsrc())
                        .into(iv03);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return convertView;

    }
    private static final int ITEM_TYPE_WITH_1_IMAGE = 0;
    private static final int ITEM_TYPE_WITH_3_IMAGE = 1;
    public int getItemViewType(int position) {
        NewsTopbean.ResultBean item = getItem(position);
        if (item.getImgextra() == null || item.getImgextra().size() == 0) {
            // 只有一张图片的item
            return ITEM_TYPE_WITH_1_IMAGE;
        } else {
            // item中有三张图片
            return ITEM_TYPE_WITH_3_IMAGE;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
}

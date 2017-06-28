package com.example.administrator.newsclient.Fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.administrator.newsclient.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yls on 2017/6/27.
 */

public  class Fragment01 extends BaseFragment{
    private TabLayout mTabLayout;
    private ViewPager mViewPager_02;
    @Override
    public int getLayout() {
        return R.layout.fragment_01;
    }

    @Override
    public void initView() {
        mTabLayout= (TabLayout) mView.findViewById(R.id.tab_layout);
        mViewPager_02= (ViewPager) mView.findViewById(R.id.view_pager_02);
        initViewPager();
    }

    private void initViewPager() {
        final String[] titles = new String[] {
                "头条", "社会", "科技", "财经", "体育", "汽车"
        };
        final String[] channelId = new String[] {
                "T1348647909107",
                "T1348648037603",
                "T1348649580692",
                "T1348648756099",
                "T1348649079062",
                "T1348654060988",
        };
        final List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            NewFragment fragment = new NewFragment();
            fragment.setChannelId(channelId[i]);
            fragments.add(fragment);
        }
        mViewPager_02.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        // 关联ViewPager和TabLayout显示
        mTabLayout.setupWithViewPager(mViewPager_02);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }



}

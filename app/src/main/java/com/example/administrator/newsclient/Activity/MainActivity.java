package com.example.administrator.newsclient.Activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.example.administrator.newsclient.Fragment.Fragment01;
import com.example.administrator.newsclient.Fragment.Fragment02;
import com.example.administrator.newsclient.Fragment.Fragment03;
import com.example.administrator.newsclient.Fragment.Fragment04;
import com.example.administrator.newsclient.Fragment.Fragment05;
import com.example.administrator.newsclient.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private Toolbar mToolbar;
private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        radiobuttonListener();

        viewpagerlistener();

        navigationLisstener();


    }

    private void radiobuttonListener() {
        //点击底部栏选项viewpager切换页面

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_01:mViewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_02:mViewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_03:mViewPager.setCurrentItem(2);
                        break;
                    case R.id.rb_04:mViewPager.setCurrentItem(3);
                        break;
                    case R.id.rb_05:mViewPager.setCurrentItem(4);
                        break;
                }
            }
        });
    }

    private void viewpagerlistener() {
        //滑动viewpager底部栏改变选项的颜色
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:mRadioGroup.check(R.id.rb_01);
                        break;
                    case 1:mRadioGroup.check(R.id.rb_02);
                        break;
                    case 2:mRadioGroup.check(R.id.rb_03);
                        break;
                    case 3:mRadioGroup.check(R.id.rb_04);
                        break;
                    case 4:mRadioGroup.check(R.id.rb_05);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void navigationLisstener() {
        //点击侧滑栏的item做出的处理事件
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.
                OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_01:showToast("你好");
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.item_02:showToast("你好");
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.item_03:showToast("你好");
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.item_04:showToast("你好");
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.item_05:showToast("你好");
                        mDrawerLayout.closeDrawers();
                        break;

                }


                return true;
            }
        });
    }

    @Override
    public void initView() {
        mRadioGroup= (RadioGroup) findViewById(R.id.rg_01);
        initViewPager();
      inittoobar();
        mNavigationView= (NavigationView) findViewById(R.id.navigation_view);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawerlayout);
        initactionbarWithDrawerlayout();



    }
private ActionBarDrawerToggle toggle;
    private void initactionbarWithDrawerlayout() {
        toggle=new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,0,
                0);
    }

    private void inittoobar() {
        mToolbar= (Toolbar) findViewById(R.id.toobar);
//        mToolbar.setLogo(R.drawable.home_tab_01_normal);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("不顾一切的奋斗");   // 通过代码设置才生效：app:title="toolbar"

//        mToolbar.setSubtitle("这是子标题");
//        mToolbar.setTitleTextColor(Color.BLUE);
        mToolbar.setSubtitleTextColor(Color.YELLOW);
        // 导航栏图标显示
        mToolbar.setNavigationIcon(R.drawable.home_tab_01_normal);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    //================Toolbar右上角弹出菜单(begin)=======================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_option, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_06:showToast("haha");
                break;
            case R.id.item_07:showToast("hehe");
                break;
        }



        return super.onOptionsItemSelected(item);
    }

    //================Toolbar右上角弹出菜单(end)=========================


    private void initViewPager() {
        mViewPager= (ViewPager) findViewById(R.id.view_pager);
        final List<Fragment> fragmentList=new ArrayList<>();
        fragmentList.add(new Fragment01());
        fragmentList.add(new Fragment02());
        fragmentList.add(new Fragment03());
        fragmentList.add(new Fragment04());
        fragmentList.add(new Fragment05());
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {



                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
    }
}

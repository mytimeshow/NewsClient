package com.example.administrator.newsclient.Activity;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.newsclient.R;

import java.io.IOException;

public class GuideActivity extends BaseActivity {
    private Button button;
    private ImageView imageView;
    private MediaPlayer musicplay=new MediaPlayer();
    private Handler mhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
         switch (msg.what){
             case 0:
                 staertAnimation();
                 break;
         }
        }
    };
    //播放 音乐
    public void startMusic(){
        try {
            AssetFileDescriptor assets=getAssets().openFd("new_version.mp3");
            musicplay.setDataSource(assets.getFileDescriptor(),assets.getStartOffset(),assets.getLength());
            musicplay.setLooping(true);
            musicplay.prepare();
            musicplay.start();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        //开始播放音乐
        startMusic();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(musicplay!=null){
            musicplay.stop();
            musicplay.release();
            musicplay=null;
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_guide;
    }


    private int index=0;
    private int[] imgarray = {R.drawable.ad_new_version1_img1, R.drawable.ad_new_version1_img2,
            R.drawable.ad_new_version1_img3, R.drawable.ad_new_version1_img4,
            R.drawable.ad_new_version1_img5,
            R.drawable.ad_new_version1_img6, R.drawable.ad_new_version1_img7};






    public void initView() {
        button = (Button) findViewById(R.id.btn_enterMainactivity);
        imageView= (ImageView) findViewById(R.id.iv_img);



    }
    public void initData() {
        staertAnimation();

    }

    private void staertAnimation() {
        index++;
        index=index%imgarray.length;

        imageView.setBackgroundResource(imgarray[index]);
        imageView.setScaleX(1.0f);
        imageView.setScaleY(1.0f);

        imageView.animate().scaleX(1.3f).scaleY(1.3f).setDuration(3000).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mhandler.sendEmptyMessageDelayed(0,1000);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).start();
    }

    @Override
    public void initListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}

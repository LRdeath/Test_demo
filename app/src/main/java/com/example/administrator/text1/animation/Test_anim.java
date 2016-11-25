package com.example.administrator.text1.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.administrator.text1.R;

/**
 * Created by WeiZ on 2016/9/22.
 */
public class Test_anim extends Activity implements  View.OnClickListener {
    private ImageView imageView;
    private Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_anim);
        imageView = (ImageView) findViewById(R.id.img_anim);
        findViewById(R.id.btn_anim_alpha).setOnClickListener(this);
        findViewById(R.id.btn_anim_rotate).setOnClickListener(this);
        findViewById(R.id.btn_anim_translate).setOnClickListener(this);
        findViewById(R.id.btn_anim_scale).setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_anim_alpha:
                animation = AnimationUtils.loadAnimation(this,R.anim.anim_alph);
                break;
            case R.id.btn_anim_rotate:
                animation = AnimationUtils.loadAnimation(this,R.anim.anim_rotate);
                break;
            case R.id.btn_anim_translate:
                animation = AnimationUtils.loadAnimation(this,R.anim.anim_translate);
                break;
            case R.id.btn_anim_scale:
                animation = AnimationUtils.loadAnimation(this,R.anim.anim_scale);
                break;

        }
        if (null != animation){
            imageView.startAnimation(animation);
        }
    }
}

package com.example.administrator.text1.test_bottommenu;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.text1.R;

/**
 * Created by Administrator on 2015/11/10.
 */
public class plusmenu_fragment extends Fragment implements View.OnClickListener {
    private View menu1;
    private View menu2;
    private View menu3;
    private ViewGroup viewGroup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bottommenu_plus_menu, null);
        menu1 = view.findViewById(R.id.plus_menu_1);
        menu2 = view.findViewById(R.id.plus_menu_2);
        menu3 = view.findViewById(R.id.plus_menu_3);
        viewGroup = (ViewGroup) view.findViewById(R.id.qwer);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.plus_menu_1:
                Toast.makeText(getActivity(), "联系客服", Toast.LENGTH_SHORT).show();
                break;
            case R.id.plus_menu_2:
                Toast.makeText(getActivity(), "机器人", Toast.LENGTH_SHORT).show();
                break;
            case R.id.plus_menu_3:
                Toast.makeText(getActivity(), "紧急呼叫", Toast.LENGTH_SHORT).show();
                break;

            default:
                finshview();
                break;

        }
    }

    private void finshview() {
        pauseEvent();
        closemenu(viewGroup);
    }

    private void pauseEvent() {
        if(getView()!=null)getView().setOnClickListener(null);
        menu1.setOnClickListener(null);
        menu2.setOnClickListener(null);
        menu3.setOnClickListener(null);

    }

    private void closemenu(ViewGroup viewGroup) {
        final int startinvent = 50;
        final int duration = 500;
        for (int i = 0; i <viewGroup.getChildCount();i++){
            final View child= viewGroup.getChildAt(i);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    TranslateAnimation close = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,
                            Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_PARENT,1f);
                    close.setDuration(duration);
                    close.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            child.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    child.startAnimation(close);
                }
            },i*startinvent);

        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        },startinvent*viewGroup.getChildCount()+duration);

    }

    /*移除“加号”菜单*/
    public void dismiss() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .remove(this)
                .commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        showview(viewGroup);
    }

    /*按钮出现动画*/
    private void showview(ViewGroup viewGroup) {
        final int duration = 300;
        final int startInterval = 50;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            final View child = viewGroup.getChildAt(i);
            child.setVisibility(View.INVISIBLE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    AnimationSet animationSet = new AnimationSet(true);
                    TranslateAnimation startanimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                            Animation.RELATIVE_TO_PARENT, 1f, Animation.RELATIVE_TO_SELF, -0.15f);
                    startanimation.setDuration(duration);
                    animationSet.addAnimation(startanimation);
                    TranslateAnimation backanimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                            Animation.RELATIVE_TO_SELF, -0.15f, Animation.RELATIVE_TO_SELF, 0f);
                    backanimation.setDuration(duration / 5);
                    backanimation.setStartOffset(duration);
                    animationSet.addAnimation(backanimation);
                    animationSet.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            child.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    child.startAnimation(animationSet);
                }
            }, i * startInterval);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    inevent();
                }
            }, viewGroup.getChildCount() * startInterval + duration);
        }
    }


    private void inevent() {
        if (getView() != null) getView().setOnClickListener(this);
        menu2.setOnClickListener(this);
        menu1.setOnClickListener(this);
        menu3.setOnClickListener(this);
    }

}

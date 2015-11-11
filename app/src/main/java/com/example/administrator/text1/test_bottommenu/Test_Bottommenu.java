package com.example.administrator.text1.test_bottommenu;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.text1.R;

/**
 * Created by Administrator on 2015/11/10.
 */
public class Test_Bottommenu extends FragmentActivity implements View.OnClickListener {
    private ImageButton btn_puls;
    private TextView textView;
    private plusmenu_fragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_bottommenu);
        btn_puls = (ImageButton) findViewById(R.id.btn_plus_menu);
        textView = (TextView) findViewById(R.id.test_bottommenu_tv);
        findViewById(R.id.plus_menu_bottom1).setOnClickListener(this);
        findViewById(R.id.plus_menu_bottom2).setOnClickListener(this);
        findViewById(R.id.plus_menu_bottom3).setOnClickListener(this);
        findViewById(R.id.plus_menu_bottom4).setOnClickListener(this);
        btn_puls.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_plus_menu:
                fragment = new plusmenu_fragment();
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.fab_in,R.anim.fab_out,R.anim.fab_in,R.anim.fab_out)
                        .replace(R.id.fragment_plus_menu, fragment)
                        .commit();
                break;
            case R.id.plus_menu_bottom1:
                textView.setText("啦啦啦");
                break;
            case R.id.plus_menu_bottom2:
                textView.setText("啦啦啦二手");
                break;
            case R.id.plus_menu_bottom3:
                textView.setText("啦啦啦大赛");
                break;
            case R.id.plus_menu_bottom4:
                textView.setText("啦啦啦软糖");
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if( fragment!= null && fragment.isVisible()){
                fragment.dismiss();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}

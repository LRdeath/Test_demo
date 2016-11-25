package com.example.administrator.text1.test_designsupport;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.TableLayout;

import com.example.administrator.text1.R;

/**
 * Created by Administrator on 2015/11/10.
 */
public class Test_DesignSupport extends AppCompatActivity {
    private TextInputLayout tv_password;
    private TextInputLayout tv_num;
    private DrawerLayout mdrawerLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigationview);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_design);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);//左上角的图标显示

        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ViewPager viewPager = (ViewPager) findViewById(R.id.design_viewpager);
        if(viewPager!=null){

        }
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tab_design);








        /*tv_num = (TextInputLayout) findViewById(R.id.test_ds_1);
        tv_password = (TextInputLayout) findViewById(R.id.test_ds_2);
        tv_password.setHint("密码");
        final TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.til_pwd);

        EditText editText = textInputLayout.getEditText();
        textInputLayout.setHint("Password");
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() > 4) {
                    textInputLayout.setError("Password error");
                    textInputLayout.setErrorEnabled(true);
                } else {
                    textInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });*/
    }
}

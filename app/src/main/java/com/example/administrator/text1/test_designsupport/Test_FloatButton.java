package com.example.administrator.text1.test_designsupport;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.text1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/24.
 */
public class Test_FloatButton extends Activity implements View.OnClickListener {
    private FloatingActionButton floatbutton;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_support_main);
        listView = (ListView) findViewById(R.id.support_listview);
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            list.add("测试Item----" + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        floatbutton = (FloatingActionButton) findViewById(R.id.support_floatbutton);
        floatbutton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.support_floatbutton:
                showSnakebar(v);
                break;
        }
    }

    private void showSnakebar(View v) {
        Snackbar.make(v, "finish", Snackbar.LENGTH_SHORT)
                .setActionTextColor(getResources().getColor(R.color.error_color))
                .setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).show();
    }
}

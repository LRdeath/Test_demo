package com.example.administrator.text1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.text1.RecyclerView.Main_RecyclerView;
import com.example.administrator.text1.animation.Test_anim;
import com.example.administrator.text1.chess.MyChess;
import com.example.administrator.text1.test_bottommenu.Test_Bottommenu;
import com.example.administrator.text1.test_designsupport.Test_DesignSupport;
import com.example.administrator.text1.test_baidumap.Test_baidumap;
import com.example.administrator.text1.test_media.Test_Media;
import com.example.administrator.text1.test_swipdelete.Test_swipdelete;

/**
 * Created by Administrator on 2015/10/23.
 */
public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listView = (ListView) findViewById(R.id.main_listview);
        String[] list = {"测试异步任务", "retrofit框架", "ListView滑动删除", "定位当前（百度地图API）","最近流行的底部菜单","DesignSupprot库练习","RecyclerView练习","基础动画效果","五子棋","音乐播放器"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Class start = null;
        switch (position) {
            case 0:
                start = Test_Asynctask.class;
                break;
            case 1:
                start = Test_Gson.class;
                break;
            case 2:
                start = Test_swipdelete.class;
                break;
            case 3:
                start = Test_baidumap.class;
                break;
            case 4:
                start = Test_Bottommenu.class;
                break;
            case 5:
                start = Test_DesignSupport.class;
                break;
            case 6:
                start = Main_RecyclerView.class;
                break;
            case 7:
                start = Test_anim.class;
                break;
            case 8:
                start = MyChess.class;
                break;
            case 9:
                start = Test_Media.class;
                break;
        }
        if (start != null){
            Intent intent = new Intent(MainActivity.this,start);
            startActivity(intent);
        }
    }
}

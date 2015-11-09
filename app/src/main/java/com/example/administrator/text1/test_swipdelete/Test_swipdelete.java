package com.example.administrator.text1.test_swipdelete;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.administrator.text1.R;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/27.
 */
public class Test_swipdelete extends Activity implements SwipdeleteListview.RemoveListener {
    private SwipdeleteListview swipdeleteListview;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_swipdelete);
        swipdeleteListview = (SwipdeleteListview) findViewById(R.id.test_swipdelete);
        swipdeleteListview.setRemoveListener(this);
        List<String> list = new ArrayList<>();
        for (int i = 0 ;i < 20;i++){
            list.add("滑动删除"+i);
        }
         adapter = new ArrayAdapter<String>(Test_swipdelete.this,android.R.layout.simple_list_item_1,list);
        swipdeleteListview.setAdapter(adapter);
    }

    @Override
    public void removeItem(SwipdeleteListview.RemoveDirection direction, int position) {
        adapter.remove(adapter.getItem(position));

    }
}

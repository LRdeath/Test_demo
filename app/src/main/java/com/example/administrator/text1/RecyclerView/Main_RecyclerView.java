package com.example.administrator.text1.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.text1.R;
import com.example.administrator.text1.tool.MyTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WeiZ on 2016/9/7.
 */
public class Main_RecyclerView extends Activity implements Adapter_RecyclerView.OnRecyclerViewListenter {
    private RecyclerView recyclerView;
    private Adapter_RecyclerView adapter;
    private List<Item_RecyclerView> list = new ArrayList<Item_RecyclerView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_main);

        recyclerView = (RecyclerView) this.findViewById(R.id.main_recyclerview);
        recyclerView.setHasFixedSize(true);//设置固定大小
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        initData();//初始化数据
        adapter = new Adapter_RecyclerView(list);
        adapter.setOnRecyclerViewListenter(this);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        Item_RecyclerView item = new Item_RecyclerView();
        for (int i = 0; i < 10; i++) {
            item.setTitle("啦啦啦新闻" + i);
            item.setContent("啦啦啦新闻内容" + i);
            list.add(item);
        }
    }

    @Override
    public void onItemClick(int position) {
        MyTools.MyToast("点击了第" + position + "个", Main_RecyclerView.this);
    }

    @Override
    public boolean onTtemLongClick(int position) {
        adapter.notifyItemRemoved(position);
        list.remove(position);
        adapter.notifyItemRangeChanged(position, adapter.getItemCount());
        return false;
    }
}

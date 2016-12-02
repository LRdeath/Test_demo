package com.example.administrator.text1.test_designsupport;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.text1.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/28.
 */

public class ListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv  = (RecyclerView) inflater.inflate(R.layout.listview_text,container,false);
        setupRecyclerView(rv);
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    public void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        //recyclerView.setAdapter();
    }
}

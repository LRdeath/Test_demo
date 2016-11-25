package com.example.administrator.text1.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.text1.R;

import java.util.List;

/**
 * Created by WeiZ on 2016/9/7.
 */
public class Adapter_RecyclerView extends RecyclerView.Adapter {




    public static interface OnRecyclerViewListenter{
        void onItemClick(int position);
        boolean onTtemLongClick(int position);
    }
    private OnRecyclerViewListenter onRecyclerViewListenter;
    public static final String TAG = Adapter_RecyclerView.class.getSimpleName();

    public void  setOnRecyclerViewListenter(OnRecyclerViewListenter onRecyclerViewListenter){
        this.onRecyclerViewListenter = onRecyclerViewListenter;
    }

    private List<Item_RecyclerView> list;
    public Adapter_RecyclerView(List<Item_RecyclerView> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_main,null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new ItemViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        viewHolder.position = position;
        Item_RecyclerView item_recyclerView = list.get(position);
        viewHolder.title.setText(item_recyclerView.getTitle());
        viewHolder.content.setText(item_recyclerView.getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private View rootView;
        private TextView title;
        private TextView content;
        private int position;

        public ItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_title);
            content = (TextView) itemView.findViewById(R.id.item_content);
            rootView = itemView.findViewById(R.id.item_root);
            rootView.setOnClickListener(this);
            rootView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(null != onRecyclerViewListenter){
                onRecyclerViewListenter.onItemClick(position);
            }
        }
        public boolean onLongClick(View v){
            if(null != onRecyclerViewListenter){
                return onRecyclerViewListenter.onTtemLongClick(position);
            }
            return false;
        }
    }
}

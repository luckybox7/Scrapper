package com.ywc.scrapper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ywc.scrapper.R;
import com.ywc.scrapper.manager.DBmanager;
import com.ywc.scrapper.model.Content;

import io.realm.RealmResults;

/**
 * Created by Yongwon on 2017. 2. 8..
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    private Context context;
    private RealmResults<Content> itemList;

    public ItemAdapter(Context context, RealmResults<Content> items) {
        this.context=context;
        this.itemList=items;
    }

    // 뷰 홀더를 어떻게 생성할 것인가??
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.content,null);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }


    // 뷰홀더를 데이터와 바인딩 할 때 어떻게 할 것인가??
    // 리스트의 한 영역에 holder를 이용, ui접근 후 데이터를 그려준다??
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        final Content item = itemList.get(position); // 뷰 순서대로 위치 파악

        Glide.with(context).load(item.getImage()).centerCrop().into(holder.thumbnail);
        holder.titleText.setText(item.getTitle());
        holder.bodyText.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        TextView titleText;
        TextView bodyText;
        TextView dateText;

        public ItemViewHolder(View itemView) {
            super(itemView);
            thumbnail = (ImageView)itemView.findViewById(R.id.thumbnail);
            titleText = (TextView)itemView.findViewById(R.id.title);
            bodyText = (TextView)itemView.findViewById(R.id.body);
            dateText = (TextView)itemView.findViewById(R.id.date);
        }
    }
}

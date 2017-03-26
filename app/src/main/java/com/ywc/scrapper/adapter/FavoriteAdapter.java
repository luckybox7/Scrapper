package com.ywc.scrapper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ywc.scrapper.R;
import com.ywc.scrapper.manager.DBmanager;
import com.ywc.scrapper.model.Content;

import java.text.SimpleDateFormat;

import io.realm.RealmResults;

/**
 * Created by Yongwon on 2017. 2. 8..
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ItemViewHolder>{

    private Context context;
    private RealmResults<Content> itemList;

    public FavoriteAdapter(Context context, RealmResults<Content> items) {
        this.context=context;
        this.itemList=items;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // 뷰 홀더를 어떻게 생성할 것인가??
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.content,null);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {

        //// TODO: 2017. 3. 19. date format 체크 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd "+" a hh:mm");
        
        final Content item = itemList.get(position); // 뷰 순서대로 위치 파악

        Glide.with(context).load(item.getImage()).centerCrop().into(holder.thumbnail);
        holder.titleText.setText(item.getTitle());
        holder.bodyText.setText(item.getDescription());
        holder.dateText.setText(dateFormat.format(item.getDate()));
        holder.favorite.setImageResource(R.drawable.btn_starred_s);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        ImageView favorite;
        TextView titleText;
        TextView bodyText;
        TextView dateText;

        public ItemViewHolder(final View itemView) {
            super(itemView);

            thumbnail = (ImageView)itemView.findViewById(R.id.thumbnail);
            favorite = (ImageView)itemView.findViewById(R.id.favorite);
            titleText = (TextView)itemView.findViewById(R.id.title);
            bodyText = (TextView)itemView.findViewById(R.id.body);
            dateText = (TextView)itemView.findViewById(R.id.date);

        }
    }

}

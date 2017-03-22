package com.ywc.scrapper.adapter;

import android.content.Context;
import android.support.v7.view.ActionMode;
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
import com.ywc.scrapper.model.Content;

import java.text.SimpleDateFormat;

import io.realm.RealmResults;

/**
 * Created by Yongwon on 2017. 2. 8..
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    private Context context;
    private RealmResults<Content> itemList;
    private android.view.ActionMode actionMode;


    public ItemAdapter(Context context, RealmResults<Content> items) {
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


    // 뷰홀더를 데이터와 바인딩 할 때 어떻게 할 것인가??
    // 리스트의 한 영역에 holder를 이용, ui접근 후 데이터를 그려준다??
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        //// TODO: 2017. 3. 19. date format 체크 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd "+" a hh:mm");
        
        final Content item = itemList.get(position); // 뷰 순서대로 위치 파악

        Glide.with(context).load(item.getImage()).centerCrop().into(holder.thumbnail);
        holder.titleText.setText(item.getTitle());
        holder.bodyText.setText(item.getDescription());
        holder.dateText.setText(dateFormat.format(item.getDate()).toString());

    }



    public class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        TextView titleText;
        TextView bodyText;
        TextView dateText;

        SparseBooleanArray selectedItems = new SparseBooleanArray();

        public ItemViewHolder(final View itemView) {
            super(itemView);

            thumbnail = (ImageView)itemView.findViewById(R.id.thumbnail);
            titleText = (TextView)itemView.findViewById(R.id.title);
            bodyText = (TextView)itemView.findViewById(R.id.body);
            dateText = (TextView)itemView.findViewById(R.id.date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int p = getLayoutPosition();
                    Toast.makeText(itemView.getContext(), "Click Test " + p, Toast.LENGTH_SHORT).show();
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    if(actionMode == null) {
                        actionMode = v.startActionMode(mActionModeCallback);
                        actionMode.setTitle("selected");
                    }

                    if(selectedItems.get(getAdapterPosition(), false)) {
                        selectedItems.delete(getAdapterPosition());
                        v.setSelected(false);
                    }else {
                        selectedItems.put(getAdapterPosition(), true);
                        v.setSelected(true);
                    }

                    int p = getLayoutPosition();
                    Toast.makeText(itemView.getContext(), "LongClick Test " + p, Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }
    }

    private android.view.ActionMode.Callback mActionModeCallback = new android.view.ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_action_mode, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
            switch(item.getItemId()){
                case R.id.action_mode_favorite:
                    Toast.makeText(context, "favorite click", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_mode_folder:
                    Toast.makeText(context, "folder click", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_mode_delete:
                    Toast.makeText(context, "delete click", Toast.LENGTH_SHORT).show();
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(android.view.ActionMode mode) {
            actionMode = null;
        }
    };


}

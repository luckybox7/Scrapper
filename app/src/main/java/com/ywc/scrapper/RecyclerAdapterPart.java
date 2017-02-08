package com.ywc.scrapper;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Yongwon on 2017. 2. 8..
 */

public class RecyclerAdapterPart extends RecyclerView.Adapter<RecyclerAdapterPart.ViewHolder>{

    Context context;
    List<RecyclerItem> itemList;

    public RecyclerAdapterPart(Context context, List<RecyclerItem> itemList) {
        this.context=context;
        this.itemList=itemList;
    }

    // 뷰 홀더를 어떻게 생성할 것인가??
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview,null);
        return new ViewHolder(v);
    }


    // 뷰홀더를 데이터와 바인딩 할 때 어떻게 할 것인가??
    // 리스트의 한 영역에 holder를 이용, ui접근 후 데이터를 그려준다??
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final RecyclerItem item=itemList.get(position); // 뷰 순서대로 위치 파악

        Drawable drawable=context.getResources().getDrawable(item.getImage());

        holder.thumbnail.setBackground(drawable);
        holder.titleText.setText(item.getTitle());
        holder.bodyText.setText(item.getBody());

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,item.getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        CardView cardview;
        TextView titleText;
        TextView bodyText;

        public ViewHolder(View itemView) {
            super(itemView);
            thumbnail=(ImageView)itemView.findViewById(R.id.thumbnail);
            titleText = (TextView)itemView.findViewById(R.id.text1);
            bodyText = (TextView)itemView.findViewById(R.id.text2);
            cardview=(CardView)itemView.findViewById(R.id.cardview);
        }
    }
}

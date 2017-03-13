package com.ywc.scrapper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ywc.scrapper.R;
import com.ywc.scrapper.model.Folder;

import io.realm.RealmResults;

/**
 * Created by Yongwon on 2017. 3. 12..
 */

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.FolderViewHolder>{

    private Context context;
    private RealmResults<Folder> folderList;

    public FolderAdapter(Context context, RealmResults<Folder> folders) {
        this.context = context;
        this.folderList = folders;
    }

    @Override
    public FolderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.folder,null);
        FolderViewHolder folderViewHolder = new FolderViewHolder(view);
        return folderViewHolder;
    }

    @Override
    public void onBindViewHolder(FolderViewHolder holder, int position) {

        Folder folder = folderList.get(position);

        holder.folderName.setText(folder.getFolderName());
//        holder.folderItemCount.setText(folder.getContentList().size());

    }

    @Override
    public int getItemCount() {
        return folderList.size();
    }

    public class FolderViewHolder extends RecyclerView.ViewHolder {

        TextView folderName;
        TextView folderItemCount;

        public FolderViewHolder(View itemView) {
            super(itemView);
            folderName = (TextView)itemView.findViewById(R.id.folderTitle);
            folderItemCount = (TextView)itemView.findViewById(R.id.folderItemCount);
        }
    }
}

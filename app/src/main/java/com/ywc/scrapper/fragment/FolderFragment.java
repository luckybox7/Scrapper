package com.ywc.scrapper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ywc.scrapper.R;
import com.ywc.scrapper.adapter.FolderAdapter;
import com.ywc.scrapper.manager.DBmanager;
import com.ywc.scrapper.model.Folder;

import io.realm.RealmResults;

/**
 * Created by Yongwon on 2017. 2. 8..
 */

public class FolderFragment extends Fragment {

    RealmResults<Folder> folders;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_folder, container, false);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_folder);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        folders = DBmanager.getFolder();

        final FolderAdapter folderAdapter = new FolderAdapter(getActivity(), folders);

        recyclerView.setAdapter(folderAdapter);

        return view;
    }
}

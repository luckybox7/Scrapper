package com.ywc.scrapper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ywc.scrapper.R;
import com.ywc.scrapper.adapter.FavoriteAdapter;
import com.ywc.scrapper.manager.DBmanager;
import com.ywc.scrapper.model.Content;

import io.realm.RealmResults;

/**
 * Created by Yongwon on 2017. 2. 8..
 */

public class FavoriteFragment extends Fragment{

    RealmResults<Content> items;
    FavoriteAdapter favoriteAdapter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_favorite);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        items = DBmanager.getFavoriteList();
        favoriteAdapter = new FavoriteAdapter(getActivity(), items);

        recyclerView.setAdapter(favoriteAdapter);

        return view;
    }
}

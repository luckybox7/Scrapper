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
import com.ywc.scrapper.adapter.ItemAdapter;
import com.ywc.scrapper.manager.DBmanager;
import com.ywc.scrapper.model.Content;

import io.realm.RealmResults;

/**
 * Created by Yongwon on 2017. 2. 8..
 */

public class ItemFragment extends Fragment {

    RealmResults<Content> items;
    ItemAdapter itemAdapter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_item);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        statusChanged();

        return view;
    }

    public void statusChanged() {

        items = DBmanager.getItem();
        itemAdapter = new ItemAdapter(getActivity(), items);

        recyclerView.setAdapter(itemAdapter);

    }

}


//        view.findViewById(R.id.addButton).setOnClickListener(new Button.OnClickListener(){
//            public void onClick(View v) {
//
//                int position = 0;
//                items.add(position, item[4]);
//                recyclerAdapterPart.notifyItemInserted(position);
//                recyclerView.scrollToPosition(position);
//            }
//
//        });

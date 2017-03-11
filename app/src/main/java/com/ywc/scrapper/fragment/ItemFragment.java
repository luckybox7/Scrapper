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

import java.util.ArrayList;

import io.realm.RealmResults;

/**
 * Created by Yongwon on 2017. 2. 8..
 */

public class ItemFragment extends Fragment {

    RealmResults<Content> items;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        //// TODO: 2017. 3. 9. default 이미지 관리 -> Glide 이미지 처리
        // defaultImage 설정 바꾸기

        items = DBmanager.getItem();

        final ItemAdapter itemAdapter = new ItemAdapter(getActivity(), items);


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


        recyclerView.setAdapter(itemAdapter);

        return view;
    }
}

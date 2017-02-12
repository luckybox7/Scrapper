package com.ywc.scrapper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yongwon on 2017. 2. 8..
 */

public class AllFragment extends Fragment {

    final int ITEM_COUNT = 5;
    List<RecyclerItem> items;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_1, container, false);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        items = new ArrayList<>();


        final RecyclerItem[] item =new RecyclerItem[ITEM_COUNT];
        item[0]=new RecyclerItem(R.drawable.ic_account_circle_black_24dp,"#1", "테스트1");
        item[1]=new RecyclerItem(R.drawable.ic_alarm_on_black_24dp,"#2", "테스트2");
        item[2]=new RecyclerItem(R.drawable.ic_arrow_back_white,"#3", "테스트3");
        item[3]=new RecyclerItem(R.drawable.ic_cached_black_24dp,"#4", "테스트4");
        item[4]=new RecyclerItem(R.drawable.ic_alarm_on_black_24dp,"#5", "테스트5");

        for(int i=0; i<ITEM_COUNT; i++) {
            items.add(item[i]);
        }

        final RecyclerAdapterPart recyclerAdapterPart = new RecyclerAdapterPart(getActivity(), items);



        view.findViewById(R.id.addButton).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Toast.makeText(getActivity(), "hello Test", Toast.LENGTH_SHORT).show();

                int position = 0;
                items.add(position, item[4]);
//                recyclerAdapterPart.notifyDataSetChanged();
                recyclerAdapterPart.notifyItemInserted(position);
                recyclerView.scrollToPosition(position);
            }

        });


        recyclerView.setAdapter(recyclerAdapterPart);

        return view;
    }
}

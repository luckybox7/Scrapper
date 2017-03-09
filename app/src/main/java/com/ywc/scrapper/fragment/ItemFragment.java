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

/**
 * Created by Yongwon on 2017. 2. 8..
 */

public class ItemFragment extends Fragment {

    final int ITEM_COUNT = 5;
//    List<RecyclerItem> items;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        //// TODO: 2017. 3. 9. default 이미지 관리
        // defaultImage 설정 바꾸기

//        =======================================================================================================================================
//        임시코드 -> 삭제해야할 부분
//
//        items = new ArrayList<>();
//
//        final RecyclerItem[] item =new RecyclerItem[ITEM_COUNT];
//        item[0]=new RecyclerItem(R.drawable.default_img,"축구 국가대표팀", "동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라만세 무궁화 삼천리 화려강산 대한사람 대한으로 길이 보전하세");
//        item[1]=new RecyclerItem(R.drawable.default_img,"설연휴 맞이 컬러링북", "동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라만세 무궁화 삼천리 화려강산 대한사람 대한으로 길이 보전하세");
//        item[2]=new RecyclerItem(R.drawable.default_img,"새 학기 맞이 노트북 대란", "동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라만세 무궁화 삼천리 화려강산 대한사람 대한으로 길이 보전하세");
//        item[3]=new RecyclerItem(R.drawable.default_img,"아이스하키 여자 대표팀 첫 승", "동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라만세 무궁화 삼천리 화려강산 대한사람 대한으로 길이 보전하세");
//        item[4]=new RecyclerItem(R.drawable.default_img,"갤럭시S8 출시소식", "동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라만세 무궁화 삼천리 화려강산 대한사람 대한으로 길이 보전하세");
//
//        for(int i=0; i<ITEM_COUNT; i++) {
//            items.add(item[i]);
//        }

//        =======================================================================================================================================




//        final ItemAdapter recyclerAdapterPart = new ItemAdapter(getActivity(), items);



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


//        recyclerView.setAdapter(recyclerAdapterPart);

        return view;
    }
}
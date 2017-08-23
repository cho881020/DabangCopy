package kr.co.tjeit.dabangcopy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import kr.co.tjeit.dabangcopy.R;
import kr.co.tjeit.dabangcopy.RoomSearchActivity;

/**
 * Created by user on 2017-08-23.
 */

public class HomeFragment extends Fragment {

    private android.widget.LinearLayout searchBtn1;
    private LinearLayout searchBtn2;
    private LinearLayout searchBtn3;
    private LinearLayout searchBtn4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home, container, false);
        this.searchBtn4 = (LinearLayout) view.findViewById(R.id.searchBtn4);
        this.searchBtn3 = (LinearLayout) view.findViewById(R.id.searchBtn3);
        this.searchBtn2 = (LinearLayout) view.findViewById(R.id.searchBtn2);
        this.searchBtn1 = (LinearLayout) view.findViewById(R.id.searchBtn1);
        return view;
    }

//    실제 동작들은 onActivityCreated 에서 작성


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupEvents();
        setValues();
    }

    private void setValues() {

    }

    private void setupEvents() {

//        검색용 버튼들은 하는일이 모두 비슷함.
//        무조건 검색 액티비티를 띄움. => 넘겨주는 재료만 다름.
//        하는 일이 비슷하니까, 공통된 업무로 변수화 하여 저장.
        View.OnClickListener searchListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), RoomSearchActivity.class);
//                화면을 넘어갈때, 버튼에 따라 선택할 탭의 숫자가 달라져야함.
//                버튼에 따라 값이 달라지므로, 버튼에다, Tag속성을 이용해 값을 저장.

//                버튼에달린 태그. -> String -> int 변환. 2단계 변환작업. => tagInt변수에 저장.
//                v 는, 클릭된 뷰 그 자체. v에게서 태그를 빼내서 작업.
                int tagInt = Integer.parseInt(v.getTag().toString());
//                변환이 완료된 태그값을 첨부.
                intent.putExtra("선택할탭", tagInt);
                startActivity(intent);
            }
        };

//        네개의 버튼에 공통된 업무를 부여.
        searchBtn1.setOnClickListener(searchListener);
        searchBtn2.setOnClickListener(searchListener);
        searchBtn3.setOnClickListener(searchListener);
        searchBtn4.setOnClickListener(searchListener);


    }
}

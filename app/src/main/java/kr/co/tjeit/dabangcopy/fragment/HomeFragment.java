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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home, container, false);
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
        searchBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RoomSearchActivity.class);
                startActivity(intent);
            }
        });
    }
}

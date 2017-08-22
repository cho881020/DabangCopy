package kr.co.tjeit.dabangcopy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.dabangcopy.adapter.RoomAdapter;
import kr.co.tjeit.dabangcopy.data.Room;
import kr.co.tjeit.dabangcopy.util.GlobalData;

public class RoomListActivity extends BaseActivity {

    private final int REQ_FOR_FILTER = 1;

    boolean isMonthPaySelected = true;
    boolean isCharterSelected = true;

    private android.widget.ListView roomListView;
//    필터되서 출력될 수 있도록 지원해주는 출력용 리스트
    List<Room> mDisplayRoomArray = new ArrayList<>();
    RoomAdapter mAdapter;
    private android.widget.ImageView filterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
//        임시로, 이곳에서 GlobalData에 더미데이터를 채워넣음.
        GlobalData.initGlobalData();
//        원하는리스트.addAll(원본리스트)?
//        원본 리스트에 있는 모든 내용물을 복사해서 원하는 리스트에 추가해주는 메쏘드
//        차후에 필터를 동작시키기 위해 mDisplayRoomArray를 활용하는 방안으로 코딩.
        mDisplayRoomArray.addAll(GlobalData.allRooms);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RoomFilterActivity.class);
                startActivityForResult(intent, REQ_FOR_FILTER);
            }
        });

        roomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, ViewRoomDetailActivity.class);
                intent.putExtra("방데이터", mDisplayRoomArray.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_FOR_FILTER) {
            if (resultCode == RESULT_OK) {
//                실제로 데이터 필터를 적용하는 일.
//                1. 전세/월세 선택 여부를 저장

                isMonthPaySelected = data.getBooleanExtra("월세선택여부", true);
                isCharterSelected = data.getBooleanExtra("전세선택여부", true);

//                2. 선택여부를 가지고 실제로 필터

                filterAndRefreshListView();

            }
        }

    }

    private void filterAndRefreshListView() {

//        1. 출력용 리스트를 전부 비워줌.

        mDisplayRoomArray.clear();

//        2. 비워진 출력용 리스트에 조건에 맞는 방들을 추가

//        2.1 조건에 맞는지 모든 방 (GlobalData => allRooms) 들을 검사

        for (Room room : GlobalData.allRooms) {
//            전/월세가 상황이 맞는지 저장하는 변수
//            기본적으로는 조건에 맞지 않는다고 전제
            boolean isPayOk = false;

//            월세를 포함하는지?
            if (isMonthPaySelected) {
//                월세가 0보다 큰지? => 맞으면 월세다.
                if (room.getRentPay() > 0) {
                    isPayOk = true;
                }

            }

            if (isCharterSelected) {
                if (room.getRentPay() == 0) {
                    isPayOk = true;
                }
            }


//            상황이 맞다면 실제로 추가하는 부분.
            if (isPayOk) {
                mDisplayRoomArray.add(room);
            }


        }

//        리스트뷰의 새로고침
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void setValues() {
        mAdapter = new RoomAdapter(mContext, mDisplayRoomArray);
        roomListView.setAdapter(mAdapter);
    }

    @Override
    public void bindViews() {
        this.roomListView = (ListView) findViewById(R.id.roomListView);
        this.filterBtn = (ImageView) findViewById(R.id.filterBtn);
    }
}

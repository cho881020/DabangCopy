package kr.co.tjeit.dabangcopy;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.dabangcopy.adapter.RoomAdapter;
import kr.co.tjeit.dabangcopy.data.Room;
import kr.co.tjeit.dabangcopy.util.GlobalData;

public class RoomListActivity extends BaseActivity {

    private android.widget.ListView roomListView;
//    필터되서 출력될 수 있도록 지원해주는 출력용 리스트
    List<Room> mDisplayRoomArray = new ArrayList<>();
    RoomAdapter mAdapter;

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

    }

    @Override
    public void setValues() {
        mAdapter = new RoomAdapter(mContext, mDisplayRoomArray);
        roomListView.setAdapter(mAdapter);
    }

    @Override
    public void bindViews() {
        this.roomListView = (ListView) findViewById(R.id.roomListView);

    }
}

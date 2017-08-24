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

//    월세 선택 여부를 저장하는 변수 => 기본값 : true
    boolean isMonthPaySelected = true;
//    전세 선택 여부를 저장하는 변수
    boolean isCharterSelected = true;
//    원룸, 투룸, 쓰리룸 선택여부를 저장할 변수
    boolean isOneRoomeSelected = true;
    boolean isTwoRoomeSelected = true;
    boolean isThreeRoomeSelected = true;
//    최소 보증금, 최대 보증금을 저장할 변수.
    int minDeposit = 0;
    int maxDeposit = 50000;

    private android.widget.ListView roomListView;
//    필터되서 출력될 수 있도록 지원해주는 출력용 리스트
    List<Room> mDisplayRoomArray = new ArrayList<>();
    RoomAdapter mAdapter;
    private android.widget.ImageView filterBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
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
                Intent myIntent = new Intent(mContext, RoomFilterActivity.class);
                startActivityForResult(myIntent, REQ_FOR_FILTER);
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
//                 필터를 가서 && 확인을 누른

//                intent data가 첨부한 데이터를 들고있으니
//                반드시 data.get~~Extra하도록 하자.
//                getIntent() 와 별개 임을 기억하자.

                isMonthPaySelected = data.getBooleanExtra("월세선택여부", true);
                isCharterSelected = data.getBooleanExtra("전세선택여부", true);
                isOneRoomeSelected = data.getBooleanExtra("원룸선택여부", true);
                isTwoRoomeSelected = data.getBooleanExtra("투룸선택여부", true);
                isThreeRoomeSelected = data.getBooleanExtra("쓰리룸선택여부", true);

//                최소 보증금 / 최대 보증금 데이터 받아오기.
                minDeposit = data.getIntExtra("최소보증금", 0);
                maxDeposit = data.getIntExtra("최대보증금", 50000);

//                실제로 출력용 리스트에 데이터를 필터하는 메쏘드
                filterRoomList();

            }
        }


    }

    private void filterRoomList() {
//        이 메쏘드가 실제로 출력용 리스트인 mDisplayRoomArray를 가공하는 역할

//        필터 : 표시될 목록을 전부 삭제부터.
        mDisplayRoomArray.clear();

//        월세/전세 계약 방식이 조건에 맞는지를 저장하는 변수
        boolean isPayOk = false;
//        방의 갯수가 조건에 맞는지를 저장하는 변수.
        boolean isRoomCountOk = false;
//        보증금이 적절한지를 저장하는 변수
        boolean isDepositOk = false;

//        전체 데이터를 하나하나 검사.
        for (Room room : GlobalData.allRooms) {

//            1번 질문. 월세를 보여줄건지?
            if (isMonthPaySelected) {
//                1.1번 질문. 지금 검사하는 방이 실제로 월세인지?
                if (room.getRentPay() > 0) {
//                    월세를 보여줄 상황이기도 하고, 실제로 월세이기도 하니
//                    이 방은 보여줘야 한다.

                    isPayOk = true;

                }
            }

            if (isCharterSelected) {
                if (room.getRentPay() == 0) {
                    isPayOk = true;
                }
            }

//            방갯수에 대한 검사

            if (isOneRoomeSelected) {
                if (room.getRoomCount() == 1) {
                    isRoomCountOk = true;
                }
            }

            if (isTwoRoomeSelected) {
                if (room.getRoomCount() == 2) {
                    isRoomCountOk = true;
                }
            }

            if (isThreeRoomeSelected) {
                if (room.getRoomCount() == 3) {
                    isRoomCountOk = true;
                }
            }


//            보증금 검사
            if (minDeposit <= room.getDeposit() && room.getDeposit() <= maxDeposit) {
                isDepositOk = true;
            }

//            마지막 질문. 모든 조건을 만족시키는 방인지?
            if (isPayOk && isRoomCountOk && isDepositOk) {
//                모든 조건이 다 맞는 방이다.
//                실제로 출력해주자. -> 출력용 리스트에 집어넣자.

                mDisplayRoomArray.add(room);

            }

//            검사가 끝났으니, 다시 기본값을 설정
//            계약방식도, 방 갯수도 일단 안맞는다라고 전제.
            isPayOk = false;
            isRoomCountOk = false;
            isDepositOk = false;


        }

//        모든 검사가 끝나면, 필터된 데이터를 가지고 리스트뷰 새로고침
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

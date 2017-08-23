package kr.co.tjeit.dabangcopy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class RoomFilterActivity extends BaseActivity {

    private Button okBtn;
    private ToggleButton monthPayToggleBtn;
    private ToggleButton charterToggleBtn;
    private ToggleButton oneRoomToggleBtn;
    private ToggleButton twoRoomToggleBtn;
    private ToggleButton threeRoomToggleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_filter);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                이 intent는 화면을 이동하기 위한 intent가 아님
//                단순히 데이터를 첨부하는 용도로 쓰기 위한 intent
//                그래서 객체화 할때 생성자에 파라미터를 비워둠.
                Intent finishIntent = new Intent();
//                돌려보낼 데이터를 finishIntent에 첨부
                finishIntent.putExtra("월세선택여부", monthPayToggleBtn.isChecked());
                finishIntent.putExtra("전세선택여부", charterToggleBtn.isChecked());
                finishIntent.putExtra("원룸선택여부", oneRoomToggleBtn.isChecked());
                finishIntent.putExtra("투룸선택여부", twoRoomToggleBtn.isChecked());
                finishIntent.putExtra("쓰리룸선택여부", threeRoomToggleBtn.isChecked());

//                실제로 돌려보내는 작업 -> setResult, finish
//                setResult : 확인 표시, 어떤 데이터를 첨부하는지 전달
                setResult(RESULT_OK, finishIntent);
//                화면을 종료
                finish();

            }
        });

    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {

        this.threeRoomToggleBtn = (ToggleButton) findViewById(R.id.threeRoomToggleBtn);
        this.twoRoomToggleBtn = (ToggleButton) findViewById(R.id.twoRoomToggleBtn);
        this.oneRoomToggleBtn = (ToggleButton) findViewById(R.id.oneRoomToggleBtn);
        this.charterToggleBtn = (ToggleButton) findViewById(R.id.charterToggleBtn);
        this.monthPayToggleBtn = (ToggleButton) findViewById(R.id.monthPayToggleBtn);
        this.okBtn = (Button) findViewById(R.id.okBtn);
    }
}

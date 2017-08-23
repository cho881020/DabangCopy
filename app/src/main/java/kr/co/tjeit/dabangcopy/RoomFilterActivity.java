package kr.co.tjeit.dabangcopy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Locale;

import io.apptik.widget.MultiSlider;

public class RoomFilterActivity extends BaseActivity {

    private Button okBtn;
    private ToggleButton monthPayToggleBtn;
    private ToggleButton charterToggleBtn;
    private ToggleButton oneRoomToggleBtn;
    private ToggleButton twoRoomToggleBtn;
    private ToggleButton threeRoomToggleBtn;
    private io.apptik.widget.MultiSlider depositSlide;
    private android.widget.TextView currentDepositTxt;

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

        depositSlide.setOnThumbValueChangeListener(new MultiSlider.OnThumbValueChangeListener() {
            @Override
            public void onValueChanged(MultiSlider multiSlider, MultiSlider.Thumb thumb, int thumbIndex, int value) {
//                Log.d("현재 값", depositSlide.getThumb(0).getValue() + " - " + depositSlide.getThumb(1).getValue());

                int minUk = depositSlide.getThumb(0).getValue() * 500 / 10000;
                int minThousands = depositSlide.getThumb(0).getValue() * 500 % 10000;

                String minValueStr;
                if (minUk == 0) {
                    minValueStr = String.format(Locale.KOREA, "%d", minThousands);
                }
                else {
                    if (minThousands == 0) {
                        minValueStr = String.format(Locale.KOREA, "%d억", minUk);
                    }
                    else {
                        minValueStr = String.format(Locale.KOREA, "%d억%d", minUk, minThousands);
                    }

                }

                int maxUk = depositSlide.getThumb(1).getValue() * 500 / 10000;
                int maxThousands = depositSlide.getThumb(1).getValue() * 500 % 10000;

                String maxValueStr;
                if (maxUk == 0) {
                    maxValueStr = String.format(Locale.KOREA, "%d", maxThousands);
                }
                else {
                    if (maxThousands == 0) {
                        maxValueStr = String.format(Locale.KOREA, "%d억", maxUk);
                    }
                    else {
                        maxValueStr = String.format(Locale.KOREA, "%d억%d", maxUk, maxThousands);
                    }

                }

                currentDepositTxt.setText(minValueStr + " ~ " + maxValueStr);

            }
        });

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
//                보증금 첨부. 최소 보증금 / 최대 보증금
                finishIntent.putExtra("최소보증금", depositSlide.getThumb(0).getValue() * 500);
                finishIntent.putExtra("최대보증금", depositSlide.getThumb(1).getValue() * 500);

//                멀티 슬라이더의 최소/최대

                Log.d("슬라이더값", depositSlide.getThumb(0).getValue()
                        + " - " + depositSlide.getThumb(1).getValue());

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
        this.depositSlide = (MultiSlider) findViewById(R.id.depositSlide);
        this.currentDepositTxt = (TextView) findViewById(R.id.currentDepositTxt);
        this.charterToggleBtn = (ToggleButton) findViewById(R.id.charterToggleBtn);
        this.monthPayToggleBtn = (ToggleButton) findViewById(R.id.monthPayToggleBtn);
        this.okBtn = (Button) findViewById(R.id.okBtn);
    }
}

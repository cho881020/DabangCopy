package kr.co.tjeit.dabangcopy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class RoomFilterActivity extends BaseActivity {

    private android.widget.Button okBtn;
    private android.widget.ToggleButton monthPayToggleBtn;
    private android.widget.ToggleButton charterToggleBtn;

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
                Intent intent = new Intent();
                intent.putExtra("월세선택여부", monthPayToggleBtn.isChecked());
                intent.putExtra("전세선택여부", charterToggleBtn.isChecked());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.charterToggleBtn = (ToggleButton) findViewById(R.id.charterToggleBtn);
        this.monthPayToggleBtn = (ToggleButton) findViewById(R.id.monthPayToggleBtn);
        this.okBtn = (Button) findViewById(R.id.okBtn);

    }
}

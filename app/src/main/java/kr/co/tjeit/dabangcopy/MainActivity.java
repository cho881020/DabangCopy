package kr.co.tjeit.dabangcopy;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import kr.co.tjeit.dabangcopy.util.GlobalData;

public class MainActivity extends BaseActivity {


    private android.widget.LinearLayout homeFragmentLayout;
    private android.widget.LinearLayout myProfileFragmentLayout;
    private LinearLayout homeBtn;
    private LinearLayout myBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        임시로, 이곳에서 GlobalData에 더미데이터를 채워넣음.
        GlobalData.initGlobalData();

        bindViews();
        setupEvents();
        setValues();

    }

    @Override
    public void setupEvents() {

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeFragmentLayout.setVisibility(View.VISIBLE);
                myProfileFragmentLayout.setVisibility(View.GONE);
            }
        });

        myBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeFragmentLayout.setVisibility(View.GONE);
                myProfileFragmentLayout.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {

        this.myBtn = (LinearLayout) findViewById(R.id.myBtn);
        this.homeBtn = (LinearLayout) findViewById(R.id.homeBtn);
        this.myProfileFragmentLayout = (LinearLayout) findViewById(R.id.myProfileFragmentLayout);
        this.homeFragmentLayout = (LinearLayout) findViewById(R.id.homeFragmentLayout);
    }
}

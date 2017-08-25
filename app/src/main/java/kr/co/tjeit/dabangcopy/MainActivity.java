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
    private LinearLayout favoriteListFragmentLayout;
    private LinearLayout mapListFragmentLayout;
    private LinearLayout seeMoreFragmentLayout;
    private LinearLayout favoriteBtn;
    private LinearLayout mapBtn;
    private LinearLayout seeMoreBtn;

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

//        1. OnClickListener 변수화
//        2. Tag 이용

        final LinearLayout[] frags = {homeFragmentLayout, favoriteListFragmentLayout,
        mapListFragmentLayout, myProfileFragmentLayout, seeMoreFragmentLayout};

        View.OnClickListener tabListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                눌리면 할 일

//                모든 Fragment 를 다 숨겨주는 작업.
                for (LinearLayout linearLayout : frags) {
                    linearLayout.setVisibility(View.GONE);
                }

//                필요한 프래그먼트를 나타내는 작업.
//                1) 눌린 버튼에 달려있는 태그를 숫자(int)로 변환
                int index = Integer.parseInt(v.getTag().toString());
//                2) 변환된 int변수 index를 활용해 원하는 프래그먼트(를 담은 LinearLayout)를
//                   다시 표시하도록 설정.
                frags[index].setVisibility(View.VISIBLE);

            }
        };

        homeBtn.setOnClickListener(tabListner);
        favoriteBtn.setOnClickListener(tabListner);
        mapBtn.setOnClickListener(tabListner);
        myBtn.setOnClickListener(tabListner);
        seeMoreBtn.setOnClickListener(tabListner);


    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.seeMoreBtn = (LinearLayout) findViewById(R.id.seeMoreBtn);
        this.myBtn = (LinearLayout) findViewById(R.id.myBtn);
        this.mapBtn = (LinearLayout) findViewById(R.id.mapBtn);
        this.favoriteBtn = (LinearLayout) findViewById(R.id.favoriteBtn);
        this.homeBtn = (LinearLayout) findViewById(R.id.homeBtn);
        this.seeMoreFragmentLayout = (LinearLayout) findViewById(R.id.seeMoreFragmentLayout);
        this.myProfileFragmentLayout = (LinearLayout) findViewById(R.id.myProfileFragmentLayout);
        this.mapListFragmentLayout = (LinearLayout) findViewById(R.id.mapListFragmentLayout);
        this.favoriteListFragmentLayout = (LinearLayout) findViewById(R.id.favoriteListFragmentLayout);
        this.homeFragmentLayout = (LinearLayout) findViewById(R.id.homeFragmentLayout);
    }
}

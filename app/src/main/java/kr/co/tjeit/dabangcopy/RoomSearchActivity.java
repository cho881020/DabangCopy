package kr.co.tjeit.dabangcopy;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;

public class RoomSearchActivity extends BaseActivity {

    private android.widget.TabWidget tabs;
    private android.widget.LinearLayout tab1;
    private android.widget.LinearLayout tab2;
    private android.widget.LinearLayout tab3;
    private android.widget.LinearLayout tab4;
    private android.widget.FrameLayout tabcontent;
    private android.widget.TabHost searchTabHost;

    int selectedTab = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_search);
        selectedTab = getIntent().getIntExtra("선택할탭", 0);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {
        makeTabHost();

//        탭 호스트에 선택값 주기
        searchTabHost.setCurrentTab(selectedTab);

    }

    private void makeTabHost() {

//        탭 호스트를 사용하기 위해서는 반드시 setup을 먼저 진행해야함.
        searchTabHost.setup();

//        탭에 들어가는 버튼 (TabSpec)을 생성하는 작업.
//        구별자(tab1), 표시(지역) 한꺼번에 세팅.
        TabHost.TabSpec spec1 = searchTabHost.newTabSpec("tab1").setIndicator("지역");
//        이 버튼이 눌리면 보여질 화면을 달아줌. xml안에 있는 tab1 LinearLayout
        spec1.setContent(R.id.tab1);
//        버튼 / 내용물 설정이 완료된 탭스펙을 탭호스트에 달아줌.
        searchTabHost.addTab(spec1);


        TabHost.TabSpec spec2 = searchTabHost.newTabSpec("tab2").setIndicator("지하철");
        spec2.setContent(R.id.tab2);
        searchTabHost.addTab(spec2);


        TabHost.TabSpec spec3 = searchTabHost.newTabSpec("tab3").setIndicator("대학교");
        spec3.setContent(R.id.tab3);
        searchTabHost.addTab(spec3);


        TabHost.TabSpec spec4 = searchTabHost.newTabSpec("tab4").setIndicator("단지");
        spec4.setContent(R.id.tab4);
        searchTabHost.addTab(spec4);

    }

    @Override
    public void bindViews() {

        this.searchTabHost = (TabHost) findViewById(R.id.searchTabHost);
        this.tabcontent = (FrameLayout) findViewById(android.R.id.tabcontent);
        this.tab4 = (LinearLayout) findViewById(R.id.tab4);
        this.tab3 = (LinearLayout) findViewById(R.id.tab3);
        this.tab2 = (LinearLayout) findViewById(R.id.tab2);
        this.tab1 = (LinearLayout) findViewById(R.id.tab1);
        this.tabs = (TabWidget) findViewById(android.R.id.tabs);
    }
}

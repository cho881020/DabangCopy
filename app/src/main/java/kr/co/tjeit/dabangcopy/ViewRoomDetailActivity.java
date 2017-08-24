package kr.co.tjeit.dabangcopy;

import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import kr.co.tjeit.dabangcopy.adapter.PhotoViewPagerAdapter;
import kr.co.tjeit.dabangcopy.data.Room;

public class ViewRoomDetailActivity extends BaseActivity implements OnMapReadyCallback {




    private android.support.v4.view.ViewPager photosViewPager;
    private android.widget.TextView monthOrNotTxt;
    private android.widget.TextView costTxt;

    Room mRoom = null;

    PhotoViewPagerAdapter mPhotoAdapter;
    private TextView pageIndicatorTxt;
    private TextView roomTypeTxt;
    private TextView roomSizeTxt;
    private TextView stairCountTxt;
    private TextView managePayTxt;
    private TextView descriptionTxt;
    private TextView monthOrNotTxt2;
    private TextView costTxt2;
    private TextView managePayTxt2;
    private TextView descriptionTxt2;
    private com.github.mikephil.charting.charts.RadarChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_room_detail);
        mRoom = (Room) getIntent().getSerializableExtra("방데이터");
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {



    }

    @Override
    public void setValues() {

//        지도 프래그먼트를 불러오는 부분.
        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mPhotoAdapter = new PhotoViewPagerAdapter(mContext, mRoom.getPhotoURLs());
        photosViewPager.setAdapter(mPhotoAdapter);


//        월세/전세 찍어주는 기능.
        if (mRoom.getRentPay() == 0) {
//            전세일 경우
//            전세라고 출력
            monthOrNotTxt.setText("전세");
//            가격을 나타내는 숫자를 노란색으로 표시.
//            Adapter는 getResources를 바로 활용할수없어서, mContext에게 대신 가져오도록 시킴.
            costTxt.setTextColor(mContext.getResources().getColor(R.color.not_month_pay_color));

//            몇억인지 저장하는 변수
//            보증금을 만으로 나누면, 몇억인지 구해짐.
            int uk =  mRoom.getDeposit() / 10000;
//            마지막 네자리는 몇천인지 표기하는 용도로 사용.
            int thousands = mRoom.getDeposit() % 10000;

            String costStr;
            if (uk == 0) {
//                1억이 안되는 경우. Ex 6500
//                표시 양식 : 6500
                costStr = String.format(Locale.KOREA, "%d", thousands);
            }
            else if (thousands == 0) {
//                정확이 몇억 단위로 떨어지는 경우. Ex. 2억
//                표시 양식 : 2억
                costStr = String.format(Locale.KOREA, "%d억", uk);
            }
            else {
//                그외의 모든 경우
//                ?억 ? 라고 찍어주면 된다.
                costStr = String.format(Locale.KOREA, "%d억%d", uk, thousands);
            }

            costTxt.setText(costStr);

        }
        else {
            monthOrNotTxt.setText("월세");
            costTxt.setTextColor(mContext.getResources().getColor(R.color.month_pay_color));

//            양식 : 보증금/월세
            String costStr = String.format(Locale.KOREA, "%d/%d", mRoom.getDeposit(), mRoom.getRentPay());
            costTxt.setText(costStr);

        }


//        원룸 / 투룸 / 쓰리룸

        if (mRoom.getRoomCount() == 1) {
//            원룸인 상황
            roomTypeTxt.setText("원룸");
        }
        else if (mRoom.getRoomCount() == 2) {

            roomTypeTxt.setText("투룸");
        }
        else if (mRoom.getRoomCount() == 3) {

            roomTypeTxt.setText("쓰리룸");
        }


//        지하?층, 반지하, ?층

        if (mRoom.getStairCount() == 0) {
//            0층일 경우에는 반지하
            stairCountTxt.setText("반지하");
        }
        else if (mRoom.getStairCount() < 0) {
//            0보다 작은 경우, -1, -2 등등..
//            지하 1층, 지하 2층 출력 => 실제 층수를 가진 stairCount에 -를 붙여서 부호를 변경
            String stairStr = String.format(Locale.KOREA, "지하 %d층", -mRoom.getStairCount());
            stairCountTxt.setText(stairStr);
        }
        else {
//            일반 지상층 Ex. 1층, 3층
            String stairStr = String.format(Locale.KOREA, "%d층", mRoom.getStairCount());
            stairCountTxt.setText(stairStr);
        }

//        방의 넓이를 미터제곱, ㎡ 으로 출력하자.

        String roomSizeStr = String.format(Locale.KOREA, "%.1f㎡", mRoom.getRoomSize());
        roomSizeTxt.setText(roomSizeStr);

//        관리비 몇만원
        String managePayStr;
        if (mRoom.getManagePay() == 0) {
            managePayStr = "관리비 없음";
        }
        else {
            managePayStr = String.format(Locale.KOREA, "관리비 %d만", mRoom.getManagePay());
        }

        managePayTxt.setText(managePayStr);

        descriptionTxt.setText(mRoom.getDescription());



        makeRadarChart();

    }

    private void makeRadarChart() {

        chart = (RadarChart) findViewById(R.id.chart);
        chart.setBackgroundColor(Color.rgb(60, 65, 82));

        chart.getDescription().setEnabled(false);

        chart.setWebLineWidth(1f);
        chart.setWebColor(Color.LTGRAY);
        chart.setWebLineWidthInner(1f);
        chart.setWebColorInner(Color.LTGRAY);
        chart.setWebAlpha(100);


        setData();

        chart.animateXY(
                1400, 1400,
                Easing.EasingOption.EaseInOutQuad,
                Easing.EasingOption.EaseInOutQuad);

        XAxis xAxis = chart.getXAxis();
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private String[] mActivities = new String[]{"가격", "관리비", "옵션", "편의시설", "교통"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = chart.getYAxis();
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.WHITE);

    }

    @Override
    public void bindViews() {
        this.descriptionTxt2 = (TextView) findViewById(R.id.descriptionTxt2);
        this.chart = (RadarChart) findViewById(R.id.chart);
        this.managePayTxt2 = (TextView) findViewById(R.id.managePayTxt2);
        this.costTxt2 = (TextView) findViewById(R.id.costTxt2);
        this.monthOrNotTxt2 = (TextView) findViewById(R.id.monthOrNotTxt2);
        this.descriptionTxt = (TextView) findViewById(R.id.descriptionTxt);
        this.managePayTxt = (TextView) findViewById(R.id.managePayTxt);
        this.stairCountTxt = (TextView) findViewById(R.id.stairCountTxt);
        this.roomSizeTxt = (TextView) findViewById(R.id.roomSizeTxt);
        this.roomTypeTxt = (TextView) findViewById(R.id.roomTypeTxt);
        this.costTxt = (TextView) findViewById(R.id.costTxt);
        this.monthOrNotTxt = (TextView) findViewById(R.id.monthOrNotTxt);
        this.pageIndicatorTxt = (TextView) findViewById(R.id.pageIndicatorTxt);
        this.photosViewPager = (ViewPager) findViewById(R.id.photosViewPager);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng roomPoint = new LatLng(mRoom.getLatitude(), mRoom.getLongitude());

        MarkerOptions roomMarker = new MarkerOptions();
        roomMarker.position(roomPoint);
        roomMarker.title("방의 위치");
        roomMarker.snippet("좋은 방입니다.");

//        만들어낸 마커를 실제로 달아줌
        googleMap.addMarker(roomMarker);

//        지도 좌표를 옮기는 작업
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(roomPoint));

        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));


    }

    public void setData() {

        float mult = 80;
        float min = 20;
        int cnt = 5;

        ArrayList<RadarEntry> entries1 = new ArrayList<RadarEntry>();
        ArrayList<RadarEntry> entries2 = new ArrayList<RadarEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < cnt; i++) {
            float val1 = (float) (Math.random() * mult) + min;
            entries1.add(new RadarEntry(val1));

            float val2 = (float) (Math.random() * mult) + min;
            entries2.add(new RadarEntry(val2));
        }

        RadarDataSet set1 = new RadarDataSet(entries1, "이 지역 평균");
        set1.setColor(Color.rgb(103, 110, 129));
        set1.setFillColor(Color.rgb(103, 110, 129));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        RadarDataSet set2 = new RadarDataSet(entries2, "이 방");
        set2.setColor(Color.parseColor("#5E90F3"));
        set2.setFillColor(Color.rgb(121, 162, 175));
        set2.setDrawFilled(true);
        set2.setFillAlpha(180);
        set2.setLineWidth(2f);
        set2.setDrawHighlightCircleEnabled(true);
        set2.setDrawHighlightIndicators(false);

        ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
        sets.add(set2);
        sets.add(set1);

        RadarData data = new RadarData(sets);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.WHITE);

        chart.setData(data);
        chart.invalidate();
    }
}

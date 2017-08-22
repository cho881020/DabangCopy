package kr.co.tjeit.dabangcopy;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import kr.co.tjeit.dabangcopy.adapter.PhotoViewPagerAdapter;
import kr.co.tjeit.dabangcopy.data.Room;

public class ViewRoomDetailActivity extends BaseActivity {

    private android.support.v4.view.ViewPager photosViewPager;
    private android.widget.TextView monthOrNotTxt;
    private android.widget.TextView costTxt;

    Room mRoom = null;

    PhotoViewPagerAdapter mPhotoAdapter;

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
        mPhotoAdapter = new PhotoViewPagerAdapter(mContext, mRoom.getPhotoURLs());
        photosViewPager.setAdapter(mPhotoAdapter);

    }

    @Override
    public void bindViews() {

        this.costTxt = (TextView) findViewById(R.id.costTxt);
        this.monthOrNotTxt = (TextView) findViewById(R.id.monthOrNotTxt);
        this.photosViewPager = (ViewPager) findViewById(R.id.photosViewPager);
    }


}

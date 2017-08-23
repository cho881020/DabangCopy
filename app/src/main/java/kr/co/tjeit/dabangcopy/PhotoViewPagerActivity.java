package kr.co.tjeit.dabangcopy;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.dabangcopy.adapter.LargePhotoViewPagerAdapter;

public class PhotoViewPagerActivity extends BaseActivity {


    private android.support.v4.view.ViewPager photosViewPager;
    LargePhotoViewPagerAdapter mAdapter;
    List<String> urls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view_pager);
        urls = getIntent().getStringArrayListExtra("URLs");
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {
//

        mAdapter = new LargePhotoViewPagerAdapter(mContext, urls);
        photosViewPager.setAdapter(mAdapter);

    }

    @Override
    public void bindViews() {
        this.photosViewPager = (ViewPager) findViewById(R.id.photosViewPager);
    }
}

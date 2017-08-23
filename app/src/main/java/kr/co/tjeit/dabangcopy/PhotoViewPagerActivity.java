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
//    시작 페이지 수를 저장하는 변수
    int startPageNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view_pager);
        urls = getIntent().getStringArrayListExtra("URLs");
        startPageNum = getIntent().getIntExtra("startPage", 0);
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

//        뷰페이저의 현재 페이지를 설정 : setCurrentItem (페이지쪽수, 애니메이션여부);
        photosViewPager.setCurrentItem(startPageNum, false);


    }

    @Override
    public void bindViews() {
        this.photosViewPager = (ViewPager) findViewById(R.id.photosViewPager);
    }
}

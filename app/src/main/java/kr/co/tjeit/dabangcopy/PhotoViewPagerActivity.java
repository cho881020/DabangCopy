package kr.co.tjeit.dabangcopy;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

public class PhotoViewPagerActivity extends BaseActivity {

    String mURL = "";
    private com.github.chrisbanes.photoview.PhotoView photoImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view_pager);
        mURL = getIntent().getStringExtra("url");
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {
//        받아온 주소를 기반으로, 이미지 뷰에 이미지를 집어넣자.
//        Glide를 이용하면 편하다.

        Glide.with(mContext).load(mURL).into(photoImg);

    }

    @Override
    public void bindViews() {
        this.photoImg = (PhotoView) findViewById(R.id.photoImg);

    }
}

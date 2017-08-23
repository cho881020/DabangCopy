package kr.co.tjeit.dabangcopy.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

import kr.co.tjeit.dabangcopy.R;

/**
 * Created by user on 2017-08-23.
 */

public class LargePhotoViewPagerAdapter extends PagerAdapter {

    Context mContext;
    List<String> mList;
    LayoutInflater inf;

    public LargePhotoViewPagerAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    // 페이저 어댑터에서 특이한 부분.
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (LinearLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View row = inf.inflate(R.layout.large_photo_item, container, false);

//        화면에 사진을 띄워주는 코드

        String url = mList.get(position);

        PhotoView photoView = (PhotoView) row.findViewById(R.id.photoView);

        Glide.with(mContext).load(url).into(photoView);

//        만들어낸 row를 container에 붙여주자.
        container.addView(row);
        return row;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}

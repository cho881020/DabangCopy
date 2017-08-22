package kr.co.tjeit.dabangcopy.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import kr.co.tjeit.dabangcopy.R;

/**
 * Created by user on 2017-08-22.
 */

public class PhotoViewPagerAdapter extends PagerAdapter {

    Context mContext;
    List<String> mList; // 사진들의 주소를 저장하기 위함.
    LayoutInflater inf;

    public PhotoViewPagerAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object); // 구글에서 참조한 코드
    }

    // ListView할때 쓰는 Adapter의 getView랑 동일한 역할.
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View row = inf.inflate(R.layout.photo_item, container, false);

//        실제로 사진을 표시 해야함. TODO

        return row;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}

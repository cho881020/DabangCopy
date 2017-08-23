package kr.co.tjeit.dabangcopy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.dabangcopy.PhotoViewPagerActivity;
import kr.co.tjeit.dabangcopy.R;
import kr.co.tjeit.dabangcopy.data.Room;

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

        final String url = mList.get(position);

        ImageView photoImg = (ImageView) row.findViewById(R.id.photoImg);

        // 인터넷 상의 이미지를 폰에서 띄우고 싶을때 -> Glide 라이브러리
        // 라이브러리 : 도서관
        // 개발에서의 라이브러리 : 다른 (구글이 아닌) 개인/회사가 만든 프로그램 모듈
        // 다른 개인/회사 -> Third Party (3rd Party)
        // 1st (개발사 그 자체. => 구글), 2nd (퍼스트 파티와 직접 연계된 회사),
        // 3rd -> 아무 관련 없는 회사/개인.

        // 특정 기능을 수행하는 코드 덩어리. => github 에 있는것들을 분석해서 사용.

        Glide.with(mContext).load(url).into(photoImg);

        container.addView(row);


//        뷰를 누르면 다른 화면으로 넘어가도록 이벤트 붙임

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PhotoViewPagerActivity.class);
                intent.putExtra("URLs", (ArrayList<String>) mList);
                mContext.startActivity(intent);
            }
        });


        return row;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}

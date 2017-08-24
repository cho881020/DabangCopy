package kr.co.tjeit.dabangcopy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import kr.co.tjeit.dabangcopy.R;
import kr.co.tjeit.dabangcopy.data.Room;
import kr.co.tjeit.dabangcopy.data.Subway;

/**
 * Created by user on 2017-08-24.
 */

public class SubwayAdapter extends ArrayAdapter<Subway> {

    Context mContext;
    List<Subway> mList;
    LayoutInflater inf;

    public SubwayAdapter(Context context, List<Subway> list) {
        super(context, R.layout.subway_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.subway_list_item, null);
        }

        Subway data = mList.get(position);

        TextView stationNameTxt = (TextView) row.findViewById(R.id.stationNameTxt);
        TextView lineTxt1 = (TextView) row.findViewById(R.id.lineTxt1);
        TextView lineTxt2 = (TextView) row.findViewById(R.id.lineTxt2);
        TextView lineTxt3 = (TextView) row.findViewById(R.id.lineTxt3);
        TextView lineTxt4 = (TextView) row.findViewById(R.id.lineTxt4);

//        실제로 데이터 설정하기.

//        지하철역 이름 설정
        stationNameTxt.setText(data.getStationName());

        TextView[] lineTxts = {lineTxt1,lineTxt2,lineTxt3,lineTxt4};

//        모든 텍스트뷰를 일단 숨김.
        for (TextView tv : lineTxts) {
            tv.setVisibility(View.GONE);
        }

//         필요한 갯수만큼 다시 보여주기.
//        보여질때, 상황에 맞는 호선 이름을 적어준다.
        for (int i=0; i < data.getLines().size() ; i++) {
//            텍스트뷰를 받아오는 기능.
            TextView tempTxt = lineTxts[i];
//            받아오면 무조건 다시 보여준다.
            tempTxt.setVisibility(View.VISIBLE);
//            보여준 텍스트뷰에는 몇호선인지 적어준다.
            tempTxt.setText(data.getLines().get(i));
        }


        return row;
    }

}

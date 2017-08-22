package kr.co.tjeit.dabangcopy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import kr.co.tjeit.dabangcopy.R;
import kr.co.tjeit.dabangcopy.data.Room;

/**
 * Created by user on 2017-08-22.
 */

public class RoomAdapter extends ArrayAdapter<Room> {

    Context mContext;
    List<Room> mList;
    LayoutInflater inf;

    public RoomAdapter(Context context, List<Room> list) {
        super(context, R.layout.room_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.room_list_item, null);
        }

        Room data = mList.get(position);

        TextView monthOrNotTxt = (TextView) row.findViewById(R.id.monthOrNotTxt);
        TextView costTxt = (TextView) row.findViewById(R.id.costTxt);
        TextView roomTypeTxt = (TextView) row.findViewById(R.id.roomTypeTxt);
        TextView stairCountTxt = (TextView) row.findViewById(R.id.stairCountTxt);
        TextView roomSizeTxt = (TextView) row.findViewById(R.id.roomSizeTxt);
        TextView managePayTxt = (TextView) row.findViewById(R.id.managePayTxt);
        TextView hashTagTxt1 = (TextView) row.findViewById(R.id.hashTagTxt1);
        TextView hashTagTxt2 = (TextView) row.findViewById(R.id.hashTagTxt2);
        TextView descriptionTxt = (TextView) row.findViewById(R.id.descriptionTxt);

//        월세/전세 찍어주는 기능.
        if (data.getRentPay() == 0) {
//            전세일 경우
//            전세라고 출력
            monthOrNotTxt.setText("전세");
//            가격을 나타내는 숫자를 노란색으로 표시.
//            Adapter는 getResources를 바로 활용할수없어서, mContext에게 대신 가져오도록 시킴.
            costTxt.setTextColor(mContext.getResources().getColor(R.color.not_month_pay_color));

//            몇억인지 저장하는 변수
//            보증금을 만으로 나누면, 몇억인지 구해짐.
            int uk =  data.getDeposit() / 10000;
//            마지막 네자리는 몇천인지 표기하는 용도로 사용.
            int thousands = data.getDeposit() % 10000;

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
            String costStr = String.format(Locale.KOREA, "%d/%d", data.getDeposit(), data.getRentPay());
            costTxt.setText(costStr);

        }

//        원룸 / 투룸 / 쓰리룸

        if (data.getRoomCount() == 1) {
//            원룸인 상황
            roomTypeTxt.setText("원룸");
        }
        else if (data.getRoomCount() == 2) {

            roomTypeTxt.setText("투룸");
        }
        else if (data.getRoomCount() == 3) {

            roomTypeTxt.setText("쓰리룸");
        }

//        지하?층, 반지하, ?층

        if (data.getStairCount() == 0) {
//            0층일 경우에는 반지하
            stairCountTxt.setText("반지하");
        }
        else if (data.getStairCount() < 0) {
//            0보다 작은 경우, -1, -2 등등..
//            지하 1층, 지하 2층 출력 => 실제 층수를 가진 stairCount에 -를 붙여서 부호를 변경
            String stairStr = String.format(Locale.KOREA, "지하 %d층", -data.getStairCount());
            stairCountTxt.setText(stairStr);
        }
        else {
//            일반 지상층 Ex. 1층, 3층
            String stairStr = String.format(Locale.KOREA, "%d층", data.getStairCount());
            stairCountTxt.setText(stairStr);
        }

//        방의 넓이를 미터제곱, ㎡ 으로 출력하자.

        String roomSizeStr = String.format(Locale.KOREA, "%.1f㎡", data.getRoomSize());
        roomSizeTxt.setText(roomSizeStr);

//        관리비 몇만원
        String managePayStr = String.format(Locale.KOREA, "관리비 %d만", data.getManagePay());
        managePayTxt.setText(managePayStr);


        return row;
    }

}

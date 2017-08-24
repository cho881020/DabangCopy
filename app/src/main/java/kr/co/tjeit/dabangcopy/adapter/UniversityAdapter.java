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

import kr.co.tjeit.dabangcopy.R;
import kr.co.tjeit.dabangcopy.data.Subway;
import kr.co.tjeit.dabangcopy.data.University;

/**
 * Created by user on 2017-08-24.
 */

public class UniversityAdapter extends ArrayAdapter<University> {

    Context mContext;
    List<University> mList;
    LayoutInflater inf;

    public UniversityAdapter(Context context, List<University> list) {
        super(context, R.layout.university_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.university_list_item, null);
        }

        University data = mList.get(position);

        TextView titleTxt = (TextView) row.findViewById(R.id.titleTxt);
        titleTxt.setText(data.getName());

        return row;
    }

}

package kr.co.tjeit.dabangcopy.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import kr.co.tjeit.dabangcopy.MyProfileSettingActivity;
import kr.co.tjeit.dabangcopy.R;
import kr.co.tjeit.dabangcopy.util.ContextUtil;

/**
 * Created by user on 2017-08-25.
 */

public class MyProfileFragment extends Fragment {

    private android.widget.TextView dialCustomerCenter;
    private android.widget.LinearLayout myProfileBtn;
    private TextView userNameTxt;
    private TextView userPhoneTxt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_my_profile, container, false);
        this.userPhoneTxt = (TextView) v.findViewById(R.id.userPhoneTxt);
        this.userNameTxt = (TextView) v.findViewById(R.id.userNameTxt);
        this.myProfileBtn = (LinearLayout) v.findViewById(R.id.myProfileBtn);
        this.dialCustomerCenter = (TextView) v.findViewById(R.id.dialCustomerCenter);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupEvents();
        setValues();
    }

    @Override
    public void onResume() {
        super.onResume();
        userNameTxt.setText(ContextUtil.getUserName(getActivity()));
        userPhoneTxt.setText(ContextUtil.getUserPhone(getActivity()));

    }

    private void setValues() {
        userNameTxt.setText(ContextUtil.getUserName(getActivity()));
        userPhoneTxt.setText(ContextUtil.getUserPhone(getActivity()));
    }

    private void setupEvents() {

        myProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyProfileSettingActivity.class);
                startActivity(intent);
            }
        });

        dialCustomerCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:07042113951"));
                startActivity(intent);
            }
        });
    }
}

package kr.co.tjeit.dabangcopy;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RequestMoveActivity extends BaseActivity {


    private android.widget.TextView moveTypeTxt1;
    private android.widget.TextView moveTypeTxt2;
    private android.widget.TextView moveTypeTxt3;
    private android.widget.TextView moveTypeTxt4;
    private TextView selectMoveDateTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_move);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        selectMoveDateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(mContext,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

//                                날짜 => 글자 : SimpleDateFormat

//                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                String str = String.format(Locale.KOREA, "%4d-%02d-%02d", year, month+1, dayOfMonth);
                                selectMoveDateTxt.setText(str);
                            }
                        },
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final TextView[] moveTypes = {moveTypeTxt1, moveTypeTxt2, moveTypeTxt3, moveTypeTxt4};

        View.OnClickListener selectMoveTypeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (TextView tv : moveTypes) {
                    tv.setBackgroundColor(Color.parseColor("#C2C2C2"));
                }

                v.setBackgroundColor(Color.parseColor("#176DE1"));

            }
        };

        moveTypeTxt1.setOnClickListener(selectMoveTypeListener);
        moveTypeTxt2.setOnClickListener(selectMoveTypeListener);
        moveTypeTxt3.setOnClickListener(selectMoveTypeListener);
        moveTypeTxt4.setOnClickListener(selectMoveTypeListener);


    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {

        this.selectMoveDateTxt = (TextView) findViewById(R.id.selectMoveDateTxt);
        this.moveTypeTxt4 = (TextView) findViewById(R.id.moveTypeTxt4);
        this.moveTypeTxt3 = (TextView) findViewById(R.id.moveTypeTxt3);
        this.moveTypeTxt2 = (TextView) findViewById(R.id.moveTypeTxt2);
        this.moveTypeTxt1 = (TextView) findViewById(R.id.moveTypeTxt1);
    }
}

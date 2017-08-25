package kr.co.tjeit.dabangcopy;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MyProfileSettingActivity extends BaseActivity {

    private android.widget.Button logoutBtn;
    private Button changePictureBtn;
    private android.widget.Spinner selectSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_setting);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        changePictureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                performClick => 안드로이드 앱이 해당 뷰를 터치하도록.
//                selectSpinner.performClick();

                String[] items = {"사진 찍기", "카메라 롤에서 선택", "사진 삭제", "취소"};

                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        아이템이 선택되면 할 일

                        Toast.makeText(mContext, which + "번 아이템 선택", Toast.LENGTH_SHORT).show();

                    }
                });
                alert.show();


            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.setTitle("로그아웃 하시겠습니까?");
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.setNegativeButton("취소", null);
//                만들어진 경고창을 띄운다.
                alert.show();
            }
        });
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.logoutBtn = (Button) findViewById(R.id.logoutBtn);
        this.changePictureBtn = (Button) findViewById(R.id.changePictureBtn);
        this.selectSpinner = (Spinner) findViewById(R.id.selectSpinner);
    }
}

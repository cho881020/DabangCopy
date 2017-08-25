package kr.co.tjeit.dabangcopy;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyProfileSettingActivity extends BaseActivity {


    final int REQ_FOR_CAMERA = 1;
    final int REQ_FOR_GALLERY = 2;

    private android.widget.Button logoutBtn;
    private Button changePictureBtn;
    private android.widget.Spinner selectSpinner;
    private de.hdodenhof.circleimageview.CircleImageView profileImg;

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

                        if (which == 0 ) {
//                            사진 찍기가 눌린 상황

                            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(takePictureIntent, REQ_FOR_CAMERA);
                        }
                        else if (which == 1) {
//                            카메라 롤에서 선택이 눌린 상황

                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);
                            startActivityForResult(intent, REQ_FOR_GALLERY);
                        }

//                        Toast.makeText(mContext, which + "번 아이템 선택", Toast.LENGTH_SHORT).show();

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_FOR_CAMERA) {
            if (resultCode == RESULT_OK) {
//                찍힌 사진을 가지고, 프로필 사진으로 설정

//                결과물 intent가 가진 모든 데이터를 받아오자 : getExtras => Bundle 변수.
                Bundle extra = data.getExtras();
//                extra 내부에는 사진으로 촬영한 그림파일 자체. Bitmap
                Bitmap profileBitmap = (Bitmap) extra.get("data");
                profileImg.setImageBitmap(profileBitmap);

            }
        } else if (requestCode == REQ_FOR_GALLERY) {
            if (resultCode == RESULT_OK) {
//                갤러리에서 선택된 사진을 프사로 설정.

//                Uri라는 형태로 데이터 리턴.
//                Uri? 안드로이드 제공 intent
//                Ex. tel:01051123237, geo:위도,경도 => 경로를 나타낸다.
                Uri uri = data.getData();
//                갤러리를 통해 받아온것? 선택된 사진이 어디에 있는지 위치 정보.

//                경로를 찾아가서 해당 사진 파일을 Bitmap으로 받아와야함.
//                MediaStore 클래스가 사진 파일 => 비트맵으로 변환해서 가져옴.

//                try : 한번 시도해봐. try 내부는 언제 에러가 터질지 모르는 부분. (예외 발생 가능 지점)
                try {
//                uri 통해서 사진파일로 찾아감.
//                사진파일 있으면, 비트맵으로 변환. (변환을 해주는 객체 : getContentResolver())
//                그냥 이 문장만 쓰면 에러가 남. 왜? 예외처리 필요.
                    Bitmap myBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    profileImg.setImageBitmap(myBitmap);

                } catch (IOException e) {
//                    예외가 실제로 발생하면 대처하는 부분 : catch
//                    앱이 죽지 않고 실행상태를 유지하도록 대처하는 부분.

                    Toast.makeText(mContext, "사진을 불러오는 중에 에러가 발생했습니다.", Toast.LENGTH_SHORT).show();

//                    어떤 예외가 발생했는지 로그로 기록.
                    e.printStackTrace();
                }

            }
        }

    }

    @Override
    public void bindViews() {
        this.logoutBtn = (Button) findViewById(R.id.logoutBtn);
        this.changePictureBtn = (Button) findViewById(R.id.changePictureBtn);
        this.selectSpinner = (Spinner) findViewById(R.id.selectSpinner);
        this.profileImg = (CircleImageView) findViewById(R.id.profileImg);
    }
}

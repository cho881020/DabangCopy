package kr.co.tjeit.dabangcopy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.facebook.login.widget.LoginButton;

import kr.co.tjeit.dabangcopy.util.ContextUtil;

public class LoginActivity extends BaseActivity {

    private android.widget.EditText idEdt;
    private android.widget.Button loginBtn;
    private com.facebook.login.widget.LoginButton loginbutton;
    private android.widget.Button facebookLoginBtn;
    private android.widget.CheckBox autoLoginChk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
//        1. 아이디를 입력한 상태에서 로그인 버튼을 누르면 메인액티비티로 이동

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MainActivity.class);

//                1. ContextUtil을 통해 SharedPrefrences에 사용자아이디를 저장.
                ContextUtil.setUserId(mContext, idEdt.getText().toString());
//                2. MainActivity의 My 탭에서 입력한 아이디를 출력
//                  => SharedPreference를 통해
//                3. 프로필 설정 화면에서도 입력한 아이디를 SharedPreference를 통해 출력
//                4. 앱을 껐다가 키면 아이디 입력칸에도 아까 입력한 아이디를 출력



//                ContextUtil.loginUser => 내용을 채워주자.
//                여기서 바로 작업하지 말고, ContextUtil에서 세팅.

//                ContextUtil.setLoginUserInfo(idEdt.getText().toString());

                startActivity(intent);
                finish();
//                startActivity Vs ForResult
            }
        });

//        2. 프로필 설정화면에서 나타나는 이메일을, 이곳에서 입력하는 ID가 표시되도록.


//        자동로그인을 체크/해제 하면 바로바로 기록하도록.

        autoLoginChk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ContextUtil.setAutoLogin(mContext, isChecked);
            }
        });


    }

    @Override
    public void setValues() {

        boolean autoLogin = ContextUtil.getAutoLogin(mContext);
        autoLoginChk.setChecked(autoLogin);

        idEdt.setText(ContextUtil.getUserId(mContext));

    }

    @Override
    public void bindViews() {

        this.facebookLoginBtn = (Button) findViewById(R.id.facebookLoginBtn);
        this.loginbutton = (LoginButton) findViewById(R.id.login_button);
        this.loginBtn = (Button) findViewById(R.id.loginBtn);
        this.autoLoginChk = (CheckBox) findViewById(R.id.autoLoginChk);
        this.idEdt = (EditText) findViewById(R.id.idEdt);

    }
}

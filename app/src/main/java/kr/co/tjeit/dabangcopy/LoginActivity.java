package kr.co.tjeit.dabangcopy;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.login.widget.LoginButton;

public class LoginActivity extends BaseActivity {

    private android.widget.EditText idEdt;
    private android.widget.Button loginBtn;
    private com.facebook.login.widget.LoginButton loginbutton;
    private android.widget.Button facebookLoginBtn;

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
//        2. 프로필 설정화면에서 나타나는 이메일을, 이곳에서 입력하는 ID가 표시되도록.

    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {

        this.facebookLoginBtn = (Button) findViewById(R.id.facebookLoginBtn);
        this.loginbutton = (LoginButton) findViewById(R.id.login_button);
        this.loginBtn = (Button) findViewById(R.id.loginBtn);
        this.idEdt = (EditText) findViewById(R.id.idEdt);

    }
}

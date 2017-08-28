package kr.co.tjeit.dabangcopy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import kr.co.tjeit.dabangcopy.util.ContextUtil;

public class LoginActivity extends BaseActivity {

    private android.widget.EditText idEdt;
    private android.widget.Button loginBtn;
    private com.facebook.login.widget.LoginButton loginbutton;
    private android.widget.Button facebookLoginBtn;
    private android.widget.CheckBox autoLoginChk;

    CallbackManager callbackManager;
    ProfileTracker profileTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
        setupEvents();
        setValues();

        callbackManager = CallbackManager.Factory.create();
        loginbutton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                Log.d("페이스북로그인", "로그인 감지");

                if (currentProfile == null) {
//                    로그아웃 된 경우
                    Toast.makeText(mContext, "페이스북 로그아웃 성공", Toast.LENGTH_SHORT).show();
                }
                else {
//                    로그인이 된 경우
                    Toast.makeText(mContext, "로그인한 사람 : " + currentProfile.getName(), Toast.LENGTH_SHORT).show();


//                    ContextUtil을 이용해 로그인 정보를 심어준다.
                    String profileURL = "https://graph.facebook.com"
                            + currentProfile.getProfilePictureUri(500,500).getPath();
                    ContextUtil.setLoginUser(mContext, currentProfile.getName(), "받아올수없음",
                            currentProfile.getId(), profileURL);

//                    로그인에 성공했으니, 메인 화면으로 넘어가게.
                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                    finish();


                }

            }
        };

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setupEvents() {
//        1. 아이디를 입력한 상태에서 로그인 버튼을 누르면 메인액티비티로 이동

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MainActivity.class);

//                1. ContextUtil을 통해 SharedPrefrences에 사용자아이디를 저장.

                ContextUtil.setLoginUser(mContext, "A사용자", "011-222-333", idEdt.getText().toString(), "tempURL");

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

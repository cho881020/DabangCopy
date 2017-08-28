package kr.co.tjeit.dabangcopy.util;

import android.content.Context;
import android.content.SharedPreferences;

import kr.co.tjeit.dabangcopy.data.User;

/**
 * Created by user on 2017-08-22.
 */

//  메쏘드를 실행하는데, Context가 재료로 필요한 메쏘드들의 모임.
//    UserAdapter mAdapter = new (mContext
public class ContextUtil {

//    일반적으로 로그인 한 사용자 데이터는
//    저장 / 불러오기에 Context가 필요함. => ContextUtil 내부에 로그인 사용자 데이터.

    public static User loginUser =  null;


//    메모될 파일의 이름 (Preference Name) 을 생성.
    private static final String prefName = "DabangPref";

//    자동로그인 여부를 저장할때 사용하는 Tag
    private static final String AUTO_LOGIN = "AUTO_LOGIN";

//    Getter / Setter 항상 public

    public static void setAutoLogin(Context context, boolean isAutoLogin) {
//        1. 메모장 파일을 열어야함.
//        메모장을 열어주려면, 반드시 Context 객체를 통해서 열어야함. 화면 필요
//        메모장파일 : SharedPreferences
//        접근을 위한 변수 : pref
//        불러오는 방법 : context.getSharedPreferences => 화면을 통해 메모장에 접근.
//        불러오는 파일명 : prefName
//        공개 여부 (모드) : 비공개
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

//        불러낸 메모장에서, 저장기능을 불러옴 : pref.edit()
//        어떤 항목을 저장할지 : AUTO_LOGIN (자동 로그인 여부)
//        실제로 저장될 데이터 : isAutoLogin
//        저장을 확정한다. (세이브 버튼 누름) : commit()
        pref.edit().putBoolean(AUTO_LOGIN, isAutoLogin).commit();

    }


    public static boolean getAutoLogin(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

//        확인한 boolean값을 저장할 변수 : autoLogin
//        메모장에게서 boolean값을 빼내는 기능 : pref.getBoolean
//        1번째 재료 : 메모한 항목의 이름 : AUTO_LOGIN
//        2번째 재료 : 만약에 메모된 기록이 없으면 뭐라고 빼올건지 : 기본 세팅.  false

        boolean autoLogin = pref.getBoolean(AUTO_LOGIN, false);
        return autoLogin;
    }


    public static void setLoginUserInfo(String loginIdStr) {

//        로그인 한 사용자 정보를 새로 생성
        loginUser = new User();
//        생성된 사용자의 ID를 저장.
        loginUser.setLoginId(loginIdStr);

    }

    public static void logoutProcess() {
//        로그인한 사용자 정보를 파기.
//        아무도 로그인 하지 않은것으로 간주.

        loginUser = null;
//        로그인한 사용자 정보를 비워준다. => 아무도 로그인 하지 않았다.
    }


}

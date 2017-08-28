package kr.co.tjeit.dabangcopy.util;

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

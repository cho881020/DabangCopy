package kr.co.tjeit.dabangcopy.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-08-22.
 */

// 사용자의 정보를 담는 클래스
public class User implements Serializable {

//    자체 고유 속성들.
    private int userId; // DB에서 몇번째 사용자인지
    private String loginId; // 로그인할때 입력하는 아이디.
    private String name; // 사용자의 이름
    private String provider; // 자체 회원가입, 페이스북으로 로그인, 카카오톡으로 로그인
    private String profileImageURL; // 프사가 있는 URL 경로
    private String phoneNum; // 방을 보고싶다고 하면 연락받을 수 있는 폰번.

//    관계 설정 -> 내가 가지게 되는 것들 위주.

    private List<Room> likeRooms = new ArrayList<>(); // 사용자가 찜한 방 목록
    private List<Room> recentSawRooms = new ArrayList<>(); // 최근에 본 방 목록

    public User() {
    }

    public User(int userId, String name, String provider, String profileImageURL, String phoneNum) {
        this.userId = userId;
        this.name = name;
        this.provider = provider;
        this.profileImageURL = profileImageURL;
        this.phoneNum = phoneNum;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public List<Room> getLikeRooms() {
        return likeRooms;
    }

    public void setLikeRooms(List<Room> likeRooms) {
        this.likeRooms = likeRooms;
    }

    public List<Room> getRecentSawRooms() {
        return recentSawRooms;
    }

    public void setRecentSawRooms(List<Room> recentSawRooms) {
        this.recentSawRooms = recentSawRooms;
    }
}

package kr.co.tjeit.dabangcopy.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-08-22.
 */

// 공인중개사 정보를 담는 클래스
public class Realtor implements Serializable {

    private int realtorId; // 부동산 번호
    private String name; // 부동산의 상호명
    private String address; // 부동산이 위치한 주소
    private String chiefName; // 대표자 이름
    private String phoneNum; // 부동산의 연락처

//    관계
    private List<Room> managingRooms = new ArrayList<>();
    // 부동산이 관리하고 있는 방의 목록


    public Realtor() {
    }

    public Realtor(int realtorId, String name, String address, String chiefName, String phoneNum) {
        this.realtorId = realtorId;
        this.name = name;
        this.address = address;
        this.chiefName = chiefName;
        this.phoneNum = phoneNum;
    }

    public int getRealtorId() {
        return realtorId;
    }

    public void setRealtorId(int realtorId) {
        this.realtorId = realtorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getChiefName() {
        return chiefName;
    }

    public void setChiefName(String chiefName) {
        this.chiefName = chiefName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public List<Room> getManagingRooms() {
        return managingRooms;
    }

    public void setManagingRooms(List<Room> managingRooms) {
        this.managingRooms = managingRooms;
    }
}

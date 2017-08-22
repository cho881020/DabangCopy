package kr.co.tjeit.dabangcopy.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-08-22.
 */

// 방의 정보를 담는 클래스
public class Room implements Serializable {

    private int roomId; // 방의 번호
    private int deposit; // 보증금
    private int rentPay; // 월세
    private int roomCount; // 방의 갯수. 만약 1개 : 원룸 2개 : 투룸
    private double roomSize; // 방의 크기. 23.1m 소수점. => /3.3 평수
    private int stairCount; // 층수 0반지하, -1 지하 1층, 1 1층
    private int managePay; // 관리비.
    private double latitude; // 위도
    private double longitude; // 경도
    // => 위도와 경도는 지도에서 방의 위치를 찍는데 활용할 변수들.
    // 지도 라이브러리 끼워넣기를 해보게된다.
    private String description; // 상세 설명

//    관계

    private Realtor manager; // 이 방을 관리하고 있는 공인중개사
    private List<User> likeUser; // 이방을 찜한 사람의 목록

//    사진 주소 목록
    private List<String> photoURLs = new ArrayList<>();


    public Room() {
    }

    public Room(int roomId, int deposit, int rentPay, int roomCount, double roomSize, int stairCount, int managePay, double latitude, double longitude, String description, Realtor manager) {
        this.roomId = roomId;
        this.deposit = deposit;
        this.rentPay = rentPay;
        this.roomCount = roomCount;
        this.roomSize = roomSize;
        this.stairCount = stairCount;
        this.managePay = managePay;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.manager = manager;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getRentPay() {
        return rentPay;
    }

    public void setRentPay(int rentPay) {
        this.rentPay = rentPay;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public double getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(double roomSize) {
        this.roomSize = roomSize;
    }

    public int getStairCount() {
        return stairCount;
    }

    public void setStairCount(int stairCount) {
        this.stairCount = stairCount;
    }

    public int getManagePay() {
        return managePay;
    }

    public void setManagePay(int managePay) {
        this.managePay = managePay;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Realtor getManager() {
        return manager;
    }

    public void setManager(Realtor manager) {
        this.manager = manager;
    }

    public List<User> getLikeUser() {
        return likeUser;
    }

    public void setLikeUser(List<User> likeUser) {
        this.likeUser = likeUser;
    }

    public List<String> getPhotoURLs() {
        return photoURLs;
    }

    public void setPhotoURLs(List<String> photoURLs) {
        this.photoURLs = photoURLs;
    }
}

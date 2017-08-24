package kr.co.tjeit.dabangcopy.util;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.dabangcopy.data.Realtor;
import kr.co.tjeit.dabangcopy.data.Room;
import kr.co.tjeit.dabangcopy.data.Subway;
import kr.co.tjeit.dabangcopy.data.User;

/**
 * Created by user on 2017-08-22.
 */

public class GlobalData {

    public static List<User> users = new ArrayList<>();
    public static List<Realtor> realtors = new ArrayList<>();
    public static List<Room> allRooms = new ArrayList<>();

//    지하철 역 목록
    public static List<Subway> stations = new ArrayList<>();

    // 더미데이터 쌓기.
    public static void initGlobalData() {
//        1. 사용자 목록
        users.clear();
        users.add(new User(1, "AAA", "Facebook", "URL", "111-1111"));
        users.add(new User(2, "BBB", "Dabang", "URL", "222-2222"));
        users.add(new User(3, "CCC", "Kakao", "URL", "333-3333"));
        users.add(new User(4, "DDD", "Facebook", "URL", "444-4444"));
        users.add(new User(5, "EEE", "Dabang", "URL", "555-5555"));
        users.add(new User(6, "FFF", "Kakao", "URL", "666-6666"));
        users.add(new User(7, "GGG", "Facebook", "URL", "777-7777"));
        users.add(new User(8, "HHH", "Facebook", "URL", "888-8888"));
        users.add(new User(9, "III", "Dabang", "URL", "999-9999"));
//        2. 부동산 목록

        realtors.add(new Realtor(1, "A부동산", "서울", "A대표", "456-7891"));
        realtors.add(new Realtor(2, "B부동산", "인천", "B대표", "321-4567"));
        realtors.add(new Realtor(3, "C부동산", "남양주", "C대표", "234-1891"));
        realtors.add(new Realtor(4, "D부동산", "용인", "D대표", "759-6423"));
        realtors.add(new Realtor(5, "E부동산", "분당", "E대표", "214-7894"));

//        3. 방 목록

        allRooms.add(new Room(1, 500, 45, 1, 36.3, -1, 0,
                37.540784, 126.994198, "1번방에 대한 설명", realtors.get(0)));

        allRooms.get(0).getPhotoURLs().add("https://d1774jszgerdmk.cloudfront.net/1024/9e89a378-ab08-4ae8-ac56-4097132e6231");
        allRooms.get(0).getPhotoURLs().add("https://d1774jszgerdmk.cloudfront.net/1024/194ea6c4-583f-4e6e-a78d-6084a3ed2ca0");
        allRooms.get(0).getPhotoURLs().add("https://d1774jszgerdmk.cloudfront.net/1024/424f83db-9cac-4d1b-819f-5bf555a2eaa5");
        allRooms.get(0).getPhotoURLs().add("https://d1774jszgerdmk.cloudfront.net/1024/77065296-0eac-4080-afda-fe725f8e6bf9");

        allRooms.add(new Room(2, 1000, 35, 1, 26.3, 0, 5,
                37.573646, 126.929949, "2번방에 대한 설명", realtors.get(3)));
        allRooms.add(new Room(3, 1500, 15, 2, 60.6, 1, 10,
                37.528760, 126.892501, "3번방에 대한 설명", realtors.get(4)));
        allRooms.add(new Room(4, 10000, 0, 3, 90.5, 2, 3,
                37.494669, 126.802665, "4번방에 대한 설명", realtors.get(1)));
        allRooms.add(new Room(5, 11000, 0, 2, 45.5, 3, 4,
                37.579273, 127.052686, "5번방에 대한 설명", realtors.get(2)));
        allRooms.add(new Room(6, 20000, 0, 2, 55.8, 0, 8,
                37.577521, 127.050658, "6번방에 대한 설명", realtors.get(3)));
        allRooms.add(new Room(7, 300, 80, 1, 33.0, -1, 7,
                37.574545, 127.047858, "7번방에 대한 설명", realtors.get(1)));
        allRooms.add(new Room(8, 6500, 0, 3, 26.7, 2, 0,
                37.570242, 127.058275, "8번방에 대한 설명", realtors.get(0)));


//        4. 지하철 역 목록
//        지하철 역 자체를 추가
        stations.add(new Subway("종로3가"));
//        추가된 지하철 역의 호선수 추가
        stations.get(0).getLines().add("1호선");
        stations.get(0).getLines().add("3호선");
        stations.get(0).getLines().add("5호선");

        stations.add(new Subway("왕십리"));
        stations.get(1).getLines().add("2호선");
        stations.get(1).getLines().add("5호선");
        stations.get(1).getLines().add("분당선");
        stations.get(1).getLines().add("경의중앙선");

        stations.add(new Subway("신림"));
        stations.get(2).getLines().add("2호선");


        stations.add(new Subway("교대"));
        stations.get(3).getLines().add("2호선");
        stations.get(3).getLines().add("3호선");


        stations.add(new Subway("신도림"));
        stations.get(4).getLines().add("2호선");


        stations.add(new Subway("서울대입구"));
        stations.get(5).getLines().add("2호선");


        stations.add(new Subway("낙성대"));
        stations.get(6).getLines().add("2호선");


        stations.add(new Subway("방배"));
        stations.get(7).getLines().add("2호선");


        stations.add(new Subway("서초"));
        stations.get(8).getLines().add("2호선");


//        ※ 관계들을 설정

//        1번방에서 가까운 역은 종로3가다.
        allRooms.get(0).getNearStations().add(stations.get(0));

//        2번방에서 가까운 역은 신림이다.
        allRooms.get(1).getNearStations().add(stations.get(2));
//        2번방에서는 교대역도 가깝다.
        allRooms.get(1).getNearStations().add(stations.get(3));

//        3번방 : 왕십리
        allRooms.get(2).getNearStations().add(stations.get(1));

//        4번방 : 교대
        allRooms.get(5).getNearStations().add(stations.get(3));
    }

}

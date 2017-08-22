package kr.co.tjeit.dabangcopy.util;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.dabangcopy.data.Realtor;
import kr.co.tjeit.dabangcopy.data.Room;
import kr.co.tjeit.dabangcopy.data.User;

/**
 * Created by user on 2017-08-22.
 */

public class GlobalData {

    public static List<User> users = new ArrayList<>();
    public static List<Realtor> realtors = new ArrayList<>();
    public static List<Room> allRooms = new ArrayList<>();

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
        allRooms.add(new Room(8, 200, 50, 3, 26.7, 2, 0,
                37.570242, 127.058275, "8번방에 대한 설명", realtors.get(0)));


//        ※ 관계들을 설정
    }

}

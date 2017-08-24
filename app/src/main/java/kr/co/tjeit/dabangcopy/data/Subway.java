package kr.co.tjeit.dabangcopy.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-08-24.
 */

// 지하철 역에 대한 정보를 담는 클래스
public class Subway implements Serializable {

//    고유 속성
    private String stationName; // 지하철 역 명

    private List<String> lines = new ArrayList<>(); // 몇 호선 역인지?
//    종로3가. 1호선, 3호선, 5호선


    public Subway(String stationName) {
        this.stationName = stationName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }
}








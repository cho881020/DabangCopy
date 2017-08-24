package kr.co.tjeit.dabangcopy.data;

import java.io.Serializable;

/**
 * Created by user on 2017-08-24.
 */

public class University implements Serializable {
    private String name; // 학교이름

    public University() {
    }

    public University(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return getName().equals(((University)obj).getName());
    }
}

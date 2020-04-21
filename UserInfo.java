package com.Mybatis.Model;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private int ID;
    private String PASSWORD;
    private String name;

    @Override
    public String toString() {
        return "UserInfo{" +
                "ID=" + ID +
                ", PASSWORD='" + PASSWORD + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

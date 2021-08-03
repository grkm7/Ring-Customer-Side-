package com.info.bitirmeprojesi;

public class UserInt {
    private String user_key;
    private int user_id;
    private int hotel_id;
    private String user_name;
    private int user_password;

    public UserInt() {
    }

    public UserInt(String user_key, int user_id, int hotel_id, String user_name, int user_password) {
        this.user_key = user_key;
        this.user_id = user_id;
        this.hotel_id = hotel_id;
        this.user_name = user_name;
        this.user_password = user_password;
    }

    public String getUser_key() {
        return user_key;
    }

    public void setUser_key(String user_key) {
        this.user_key = user_key;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_password() {
        return user_password;
    }

    public void setUser_password(int user_password) {
        this.user_password = user_password;
    }
}

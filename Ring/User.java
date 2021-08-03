package com.info.bitirmeprojesi;

public class User {
    private String user_key;
    private String user_id;
    private String hotel_id;
    private String user_name;
    private String user_password;

    public User() {
    }

    public User(String user_key, String user_id, String hotel_id, String user_name, String user_password) {
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}

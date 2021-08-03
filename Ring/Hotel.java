package com.info.bitirmeprojesi;

public class Hotel {
    private String Hotel_key;
    private int Hotel_id;
    private String Hotel_ad;
    private String Map_path;


    public Hotel() {
    }

    public Hotel(String hotel_key, int hotel_id, String hotel_ad, String map_path) {
        Hotel_key = hotel_key;
        Hotel_id = hotel_id;
        Hotel_ad = hotel_ad;
        Map_path = map_path;
    }

    public String getHotel_key() {
        return Hotel_key;
    }

    public void setHotel_key(String hotel_key) {
        Hotel_key = hotel_key;
    }

    public int getHotel_id() {
        return Hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        Hotel_id = hotel_id;
    }

    public String getHotel_ad() {
        return Hotel_ad;
    }

    public void setHotel_ad(String hotel_ad) {
        Hotel_ad = hotel_ad;
    }

    public String getMap_path() {
        return Map_path;
    }

    public void setMap_path(String map_path) {
        Map_path = map_path;
    }
}

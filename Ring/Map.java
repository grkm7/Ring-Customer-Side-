package com.info.bitirmeprojesi;

public class Map<S, O> {
    private String Map_key;
    private String image_path;

    private int Hotel_id;

    public Map() {
    }

    public Map(String map_key, String image_path,  int hotel_id) {
        Map_key = map_key;
        this.image_path = image_path;

        Hotel_id = hotel_id;
    }

    public String getMap_key() {
        return Map_key;
    }

    public void setMap_key(String map_key) {
        Map_key = map_key;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }



    public int getHotel_id() {
        return Hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        Hotel_id = hotel_id;
    }
}

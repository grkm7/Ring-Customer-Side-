package com.info.bitirmeprojesi;

public class RoomService {
    private String RoomService_key;
    private int RoomService_id;
    private String TimeSpace;
    private int User_id;
    private int valid;
    private String service_durum;

    public RoomService() {
    }

    public RoomService(String roomService_key, int roomService_id, String timeSpace, int user_id, int valid, String service_durum) {
        RoomService_key = roomService_key;
        RoomService_id = roomService_id;
        TimeSpace = timeSpace;
        User_id = user_id;
        this.valid = valid;
        this.service_durum = service_durum;
    }

    public String getService_durum() {
        return service_durum;
    }

    public void setService_durum(String service_durum) {
        this.service_durum = service_durum;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public String getRoomService_key() {
        return RoomService_key;
    }

    public void setRoomService_key(String roomService_key) {
        RoomService_key = roomService_key;
    }

    public int getRoomService_id() {
        return RoomService_id;
    }

    public void setRoomService_id(int roomService_id) {
        RoomService_id = roomService_id;
    }

    public String getTimeSpace() {
        return TimeSpace;
    }

    public void setTimeSpace(String timeSpace) {
        TimeSpace = timeSpace;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }
}

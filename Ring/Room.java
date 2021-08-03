package com.info.bitirmeprojesi;

public class Room {
    private int room_id;
    private int room_no;

    public Room() {
    }

    public Room(int room_id, int room_no) {
        this.room_id = room_id;
        this.room_no = room_no;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getRoom_no() {
        return room_no;
    }

    public void setRoom_no(int room_no) {
        this.room_no = room_no;
    }
}

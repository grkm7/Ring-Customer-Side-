package com.info.bitirmeprojesi;

public class Item {
    private int item_id;
    private String item_key;
    private int order_type ;
    private String item_name ;
    private String item_photo ;
    private int item_price ;

    public Item() {
    }

    public Item(int item_id, String item_key, int order_type, String item_name, String item_photo, int item_price) {
        this.item_id = item_id;
        this.item_key = item_key;
        this.order_type = order_type;
        this.item_name = item_name;
        this.item_photo = item_photo;
        this.item_price = item_price;
    }


    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_key() {
        return item_key;
    }

    public void setItem_key(String item_key) {
        this.item_key = item_key;
    }

    public int getOrder_type() {
        return order_type;
    }

    public void setOrder_type(int order_type) {
        this.order_type = order_type;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_photo() {
        return item_photo;
    }

    public void setItem_photo(String item_photo) {
        this.item_photo = item_photo;
    }

    public int getItem_price() {
        return item_price;
    }

    public void setItem_price(int item_price) {
        this.item_price = item_price;
    }
}

package com.info.bitirmeprojesi;

public class Tabs {
    private int tab_id;
    private String textView ;
    private String imageView;

    public Tabs(int tab_id, int siparisServis, String order) {
    }

    public Tabs(int tab_id, String textView, String imageView) {
        this.tab_id = tab_id;
        this.textView = textView;
        this.imageView = imageView;
    }

    public int getTab_id() {
        return tab_id;
    }

    public void setTab_id(int tab_id) {
        this.tab_id = tab_id;
    }

    public String getTextView() {
        return textView;
    }

    public void setTextView(String textView) {
        this.textView = textView;
    }

    public String getImageView() {
        return imageView;
    }

    public void setImageView(String imageView) {
        this.imageView = imageView;
    }
}

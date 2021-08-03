package com.info.bitirmeprojesi;

public class Chart {
    private int chart_id;
    private String chart_name;
    private int chart_count;

    public Chart(int room_id, int room_no) {
    }

    public Chart(int chart_id, String chart_name, int chart_count) {
        this.chart_id = chart_id;
        this.chart_name = chart_name;
        this.chart_count = chart_count;
    }

    public int getChart_id() {
        return chart_id;
    }

    public void setChart_id(int chart_id) {
        this.chart_id = chart_id;
    }

    public String getChart_name() {
        return chart_name;
    }

    public void setChart_name(String chart_name) {
        this.chart_name = chart_name;
    }

    public int getChart_count() {
        return chart_count;
    }

    public void setChart_count(int chart_count) {
        this.chart_count = chart_count;
    }
}

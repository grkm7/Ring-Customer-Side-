package com.info.bitirmeprojesi;

public class DailyPlan {
    private String DailyPlan_key;
    private int DailyPlan_id;
    private String Date;
    private String Plan;

    public DailyPlan() {
    }

    public DailyPlan(String dailyPlan_key, int dailyPlan_id, String date, String plan) {
        DailyPlan_key = dailyPlan_key;
        DailyPlan_id = dailyPlan_id;
        Date = date;
        Plan = plan;
    }

    public String getDailyPlan_key() {
        return DailyPlan_key;
    }

    public void setDailyPlan_key(String dailyPlan_key) {
        DailyPlan_key = dailyPlan_key;
    }

    public int getDailyPlan_id() {
        return DailyPlan_id;
    }

    public void setDailyPlan_id(int dailyPlan_id) {
        DailyPlan_id = dailyPlan_id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getPlan() {
        return Plan;
    }

    public void setPlan(String plan) {
        Plan = plan;
    }
}

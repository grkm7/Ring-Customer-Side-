package com.info.bitirmeprojesi;

public class Schedules {
    private String saat1;
    private String saat2;
    private String program;

    public Schedules() {
    }

    public Schedules(String saat1, String saat2, String program) {
        this.saat1 = saat1;
        this.saat2 = saat2;
        this.program = program;
    }

    public String getSaat1() {
        return saat1;
    }

    public void setSaat1(String saat1) {
        this.saat1 = saat1;
    }

    public String getSaat2() {
        return saat2;
    }

    public void setSaat2(String saat2) {
        this.saat2 = saat2;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}

package com.example.washino;

public class ScheduleClass {
    String scheduleDate, scheduleTime;
    public ScheduleClass(){

    }

    public ScheduleClass(String scheduleDate, String scheduleTime) {
        this.scheduleDate = scheduleDate;
        this.scheduleTime = scheduleTime;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }
}

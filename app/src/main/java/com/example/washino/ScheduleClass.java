package com.example.washino;

public class ScheduleClass {
    String scheduleDate, scheduleTime, scheduleNotes;
    public ScheduleClass(){

    }

    public ScheduleClass(String scheduleDate, String scheduleTime, String scheduleNotes) {
        this.scheduleDate = scheduleDate;
        this.scheduleTime = scheduleTime;
        this.scheduleNotes = scheduleNotes;
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

    public String getScheduleNotes() {
        return scheduleNotes;
    }

    public void setScheduleNotes(String scheduleNotes) {
        this.scheduleNotes = scheduleNotes;
    }
}

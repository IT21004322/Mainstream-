package com.example.myapp2.model;

public class SleepModel {
    private String id;
    private String name;
    private String date;
    private int hours;
    private int minutes;

    public SleepModel(String id, String name, String date, int hours, int minutes) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.hours = hours;
        this.minutes = minutes;
    }

    public SleepModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public boolean checkNull(String hours, String minutes){
        if(hours == "" || minutes == ""){
            return true;
        }else{
            return false;
        }
    }
}

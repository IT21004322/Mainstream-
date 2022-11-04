package com.example.myapp2.Minod;

public class stepsModel {

    public stepsModel() {
    }

    private String name;
    private String date;
    private int steps;

    public stepsModel(String name, String date, int steps) {
        this.name = name;
        this.date = date;
        this.steps = steps;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;

    }
    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }



}

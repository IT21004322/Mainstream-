package com.example.myapp2.models;

import androidx.annotation.NonNull;

public class ExerciseRecords {

    private int exerciseId;
    private String name;
    private double minutes;
    private String date;
    private double caloriesBurnt;

    public ExerciseRecords(){}

    public ExerciseRecords(int exerciseId, String name, double minutes, double caloriesBurnt) {
        this.exerciseId = exerciseId;
        this.name = name;
        this.minutes = minutes;
        this.caloriesBurnt = caloriesBurnt;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMinutes() {
        return minutes;
    }

    public void setMinutes(double minutes) {
        this.minutes = minutes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCaloriesBurnt() {
        return caloriesBurnt;
    }

    public void setCaloriesBurnt(double caloriesBurnt) {
        this.caloriesBurnt = caloriesBurnt;
    }

    public boolean validateName(@NonNull String name){
        if(name == null ||name.isEmpty()){
            return false;
        }else
            return  true;
    }

    public boolean validateValues(String minutes, String calBurnt){
        if(Double.parseDouble(minutes) <0 || Double.parseDouble(calBurnt)< 0){
            return false;
        }else{
            return true;
        }
    }
}

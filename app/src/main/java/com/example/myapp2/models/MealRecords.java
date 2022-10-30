package com.example.myapp2.models;

import androidx.annotation.NonNull;

public class MealRecords {

    private int mealID;
    private String name;
    private double calories;
    private double carbohydrates;
    private double proteins;
    private double fats;
    private double vitamin;
    private double mineral;
    private double fibre;
    private String date;
    private String type;

    public MealRecords(int mealID, String name,String type, double calories,
                       double carbohydrates, double proteins, double fats, double vitamin,
                       double mineral, double fibre) {
        this.mealID = mealID;
        this.name = name;
        this.type = type;
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.proteins = proteins;
        this.fats = fats;
        this.vitamin = vitamin;
        this.mineral = mineral;
        this.fibre = fibre;
    }

    public MealRecords(){}

    public int getMealID() {
        return mealID;
    }

    public void setMealID(int mealID) {
        this.mealID = mealID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getVitamin() {
        return vitamin;
    }

    public void setVitamin(double vitamin) {
        this.vitamin = vitamin;
    }

    public double getMineral() {
        return mineral;
    }

    public void setMineral(double mineral) {
        this.mineral = mineral;
    }

    public double getFibre() {
        return fibre;
    }

    public void setFibre(double fibre) {
        this.fibre = fibre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean validateName(@NonNull String name){
        if( name == null || name.isEmpty())
            return false;
        else
            return true;
    }

    public boolean validateType(@NonNull String type){
        if (type.equalsIgnoreCase("Breakfast") ||
                type.equalsIgnoreCase("Lunch")
        || type.equalsIgnoreCase("Dinner")){
            return true;
        }
        else
            return false;
    }

    public boolean validateValues(String calories,String carbohydrates,String proteins,String fats,
             String vitamins, String minerals,String fibre){
        if(calories == null || calories.isEmpty() ||carbohydrates == null ||carbohydrates.isEmpty()
        || proteins == null || proteins.isEmpty() || fats == null ||  fats.isEmpty() ||
        vitamins == null || vitamins.isEmpty()|| minerals == null ||minerals.isEmpty()||
                fibre == null || fibre.isEmpty()){
            return false;
        }
        else
       if(Double.parseDouble(calories) < 0 || Double.parseDouble(carbohydrates) < 0
            || Double.parseDouble(proteins) <0 || Double.parseDouble(fats) <0 ||
                Double.parseDouble(vitamins)
             <0 || Double.parseDouble(minerals) <0 || Double.parseDouble(fibre) < 0){
            return false;
        }else
            return  true;
    }
}

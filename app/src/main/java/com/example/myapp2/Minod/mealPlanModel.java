package com.example.myapp2.Minod;
public class mealPlanModel {
    String name;
    String description;
    int calories;
    int carbs;
    int proteins;
    int fats;
    String type;
    String BMI;

    public mealPlanModel(String name, String description, int calories, int carbs, int proteins, int fats, String type, String BMI) {
        this.name = name;
        this.description = description;
        this.calories = calories;
        this.carbs = carbs;
        this.proteins = proteins;
        this.fats = fats;
        this.type = type;
        this.BMI = BMI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }
}

package com.example.myapp2.Minod;

public class user_model {
    private String name;
    private int age;
    private int height;
    private double startWeight;
    private double goalWeight;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getStartWeight() {
        return startWeight;
    }

    public void setStartWeight(double startWeight) {
        this.startWeight = startWeight;
    }

    public double getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(double goalWeight) {
        this.goalWeight = goalWeight;
    }

    public user_model(String name, int age, int height, double startWeight, double goalWeight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.startWeight = startWeight;
        this.goalWeight = goalWeight;
    }

    public user_model() {

    }

    public boolean checkNull(String Name, String age, String height, String weight1, String weight2){

        return(Name == null || age == null || height == null || weight1 == null || weight2== null);

    }
}

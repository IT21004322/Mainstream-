package com.example.myapp2.models;

public class WeightRecords {

    private String date;
    private String weight;
    private String change;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public boolean validateWeight(String weight){
        if(weight.isEmpty()){
            return false;
        } else if(Double.parseDouble(weight) < 0)
            return false;
        else{
            return true;
        }
    }
}

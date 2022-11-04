package com.example.myapp2.model;

public class DevLogData {
    private String id;
     private String name;
     private String date;
     private float litres;

    public DevLogData(String id,String name, String date, float litres) {

        this.id = id;
        this.name = name;
        this.date = date;
        this.litres = litres;
    }

    public DevLogData() {

    }

    public String toString(){
        return "User{"+
                "date: "+date+
                ", litres: "+litres+
                "}";
    }

    public void setId(String id){this.id = id;}
    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLitres(float litres) {
        this.litres = litres;
    }

    public String getName() {
        return name;
    }

    public String getId(){return id;}

    public String getDate() {
        return date;
    }

    public float getLitres() {
        return litres;
    }

    public boolean checkNull(String litres){
        if(litres == null){
            return true;
        }else{
            return false;
        }
    }


}

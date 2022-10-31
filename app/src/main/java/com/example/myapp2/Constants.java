package com.example.myapp2;

public class Constants {

    public static final String TABLE_NAME = "userInfo";
    public static final String ID= "ID";
    public static final String NAME= "NAME";
    public static final String EMAIL= "EMAIL";
    public static final String AGE= "AGE";
    public static final String BIRTHDATE= "BIRTHDATE";


    public static final String ADD_TIMESTAMP= "ADD_TIMESTAMP";
    public static final String UPDATE_TIMESTAMP= "UPDATE_TIMESTAMP";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME + " TEXT,"
            + EMAIL + " TEXT,"
            + AGE + " TEXT,"
            + BIRTHDATE + " TEXT,"
            + ADD_TIMESTAMP+ " TEXT,"
            + UPDATE_TIMESTAMP +" TEXT"
            + ");" ;

}

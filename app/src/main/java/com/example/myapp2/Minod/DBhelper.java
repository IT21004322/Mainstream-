package com.example.myapp2.Minod;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {

    public static final String USER_TABLE = "userData";
    public static final String USER_NAME = "name";
    public static final String AGE = "age";
    public static final String HEIGHT = "height";
    public static final String START = "start";
    public static final String GOAL = "goal";

    public static final String STEPS_TABLE = "stepsData";
    public static final String STEPS_DATE = "date";
    public static final String STEPS_COUNT = "steps";
    public static final String STEPS_USER = "name";

    public static final String MEAL_TABLE = "meals";
    public static final String MEAL_NAME = "name";
    public static final String MEAL_DESC = "description";
    public static final String MEAL_CALORIES = "calories";
    public static final String MEAL_CARBS = "carbs";
    public static final String MEAL_PROTEINS = "proteins";
    public static final String MEAL_FATS = "fats";
    public static final String MEAL_TYPE = "type";
    public static final String MEAL_BMI = "BMI";





    public DBhelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table "+ USER_TABLE +"(ID INTEGER PRIMARY KEY   AUTOINCREMENT, "
                + USER_NAME +" TEXT, "
                +AGE+" integer, "
                +HEIGHT+" integer, "
                +START+" real, "
                +GOAL+" real)");

        DB.execSQL("create Table "+STEPS_TABLE+"(ID INTEGER PRIMARY KEY   AUTOINCREMENT, "
                +STEPS_DATE+" TEXT , "
                +STEPS_COUNT+" INT, "
                +STEPS_USER +" TEXT)");

        DB.execSQL("create Table "+MEAL_TABLE+"("+MEAL_NAME+" TEXT PRIMARY KEY, "
                +MEAL_DESC+" TEXT, "
                +MEAL_CALORIES+" integer, "
                +MEAL_CARBS+" integer, "
                +MEAL_PROTEINS+" integer, "
                +MEAL_FATS+" integer, "
                +MEAL_TYPE+" TEXT, "
                +MEAL_BMI+" REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists " + USER_TABLE);
        DB.execSQL("drop table if exists " + STEPS_TABLE);
        DB.execSQL("drop table if exists " + MEAL_TABLE);
    }

    public boolean InsertUserData(com.example.sculptr.user_model UM){

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.putNull("ID");
        CV.put(USER_NAME, UM.getName());
        CV.put(AGE, UM.getAge());
        CV.put(HEIGHT, UM.getHeight());
        CV.put(START, UM.getStartWeight());
        CV.put(GOAL, UM.getGoalWeight());
        long result = DB.insert(USER_TABLE, null, CV);
        return (result != -1);
    }

    public boolean InsertMealData(com.example.sculptr.mealPlanModel MM){

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(MEAL_NAME, MM.getName());
        CV.put(MEAL_DESC, MM.getDescription());
        CV.put(MEAL_CALORIES, MM.getCalories());
        CV.put(MEAL_CARBS, MM.getCarbs());
        CV.put(MEAL_PROTEINS, MM.getProteins());
        CV.put(MEAL_FATS, MM.getFats());
        CV.put(MEAL_TYPE, MM.getType());
        CV.put(MEAL_BMI, MM.getBMI());
        long result = DB.insert(MEAL_TABLE, null, CV);
        return (result != -1);
    }
    public boolean insertStepData(com.example.sculptr.stepsModel steps){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();

        CV.putNull("ID");
        CV.put(STEPS_USER, steps.getName());
        CV.put(STEPS_DATE, steps.getDate());
        CV.put(STEPS_COUNT, steps.getSteps());

        long result = DB.insert(STEPS_TABLE, null, CV);
        return (result != -1);
    }

    public ArrayList<com.example.sculptr.user_model> getAllUsers(){
        ArrayList<com.example.sculptr.user_model> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + USER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToNext()){
            //loop through the Cursor and create new User objects, and put them in the return list
            do {
                String name = cursor.getString(2);
                int age = cursor.getInt(2);
                int height = cursor.getInt(3);
                double start = cursor.getDouble(4);
                double goal = cursor.getDouble(5);

                com.example.sculptr.user_model user = new com.example.sculptr.user_model(name, age, height,start,goal);
                returnList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public ArrayList<com.example.sculptr.mealPlanModel> mealPlanGetAll(){
        ArrayList<com.example.sculptr.mealPlanModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + MEAL_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToNext()){
            //loop through the Cursor and create new User objects, and put them in the return list
            do {
                String name = cursor.getString(0);
                String description = cursor.getString(1);
                int calories = cursor.getInt(2);
                int carbs = cursor.getInt(3);
                int proteins = cursor.getInt(4);
                int fats = cursor.getInt(5);
                String type = cursor.getString(6);
                String BMI = cursor.getString(7);

                com.example.sculptr.mealPlanModel meal = new com.example.sculptr.mealPlanModel(name,description,calories,carbs,proteins,fats,type,BMI);
                returnList.add(meal);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public ArrayList<com.example.sculptr.stepsModel> getAllStepsLogs(){
        ArrayList<com.example.sculptr.stepsModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + STEPS_TABLE ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToNext()){
            //loop through the Cursor and create new User objects, and put them in the return list
            do {
                String date = cursor.getString(1);
                int steps = cursor.getInt(2);
                String name = cursor.getString(3);

                com.example.sculptr.stepsModel step = new com.example.sculptr.stepsModel(name, date, steps);
                returnList.add(step);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public Cursor getStepDataCursor() {

        String queryString = "SELECT * FROM " + STEPS_TABLE ;
        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(queryString, null);
    }

    public boolean UpdateUserData(com.example.sculptr.user_model usermodel) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(AGE, usermodel.getAge());
        CV.put(HEIGHT, usermodel.getHeight());
        CV.put(START, usermodel.getStartWeight());
        CV.put(GOAL, usermodel.getGoalWeight());


        Cursor cur = DB.rawQuery("select * from "+USER_TABLE+" where name = ?", new String[]{usermodel.getName()});
        if (cur.getCount() > 0) {
            long result = DB.update(USER_TABLE, CV, "name = ?", new String[]{usermodel.getName()});
            return result != -1;
        } else {
            return false;
        }
    }

    public boolean UpdateStepsData(com.example.sculptr.stepsModel steps) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put(STEPS_USER, "John");
        CV.put(STEPS_COUNT, steps.getSteps());

        Cursor cur = DB.rawQuery("select * from "+STEPS_TABLE+" where date = ?", new String[]{steps.getDate()});
        if (cur.getCount() > 0) {
            long result = DB.update(STEPS_TABLE, CV, "date = ?", new String[]{steps.getDate()});
            return result != -1;
        } else {
            return false;
        }
    }

    public boolean DeleteUserData(String name) {

        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cur = DB.rawQuery("select * from "+ USER_TABLE +" where name = ?", new String[]{name});
        if (cur.getCount() > 0) {
            long result = DB.delete(USER_TABLE, "name = ?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean DeleteStepsData(String date) {

        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cur = DB.rawQuery("select * from "+ STEPS_TABLE +" where date = ?", new String[]{date});
        if (cur.getCount() > 0) {
            long result = DB.delete(STEPS_TABLE, "date = ?", new String[]{date});
            return (result != -1);
        } else {
            return false;
        }
    }

    public Cursor GetUserData() {

        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cur = DB.rawQuery("select * from "+ USER_TABLE +"", null);
        return cur;
    }

    public Cursor GetLatest() {

        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cur = DB.rawQuery("select * from "+ USER_TABLE +" order by ID DESC LIMIT 1", null);
        return cur;
    }

    public Cursor GetOneUserData(String name) {

        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cur = DB.rawQuery("select * from "+ USER_TABLE +" where name = ?", new String[]{name});
        return cur;
    }



}




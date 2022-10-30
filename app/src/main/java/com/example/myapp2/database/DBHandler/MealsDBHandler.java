package com.example.myapp2.database.DBHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapp2.database.Master.MealRecordMaster;

import java.util.ArrayList;
import java.util.List;

public class MealsDBHandler extends DBHelper {

    public MealsDBHandler(Context context) {
        super(context);
    }

    public long addMealsInfo (String date,String name, String type,String cal, String carbs,
                              String protein, String fat, String vit, String min, String fibre){
        SQLiteDatabase db = getWritableDatabase();
        //adds value to tbl
        ContentValues values = new ContentValues();
        //values.put(MealRecordMaster.Meals.COLUMN_NAME_MEALID,0);
        values.put(MealRecordMaster.Meals.COLUMN_NAME_DATE, date);
        values.put(MealRecordMaster.Meals.COLUMN_NAME_Name, name);
        values.put(MealRecordMaster.Meals.COLUMN_NAME_TYPE, type);
        values.put(MealRecordMaster.Meals.COLUMN_NAME_CALORIES, cal);
        values.put(MealRecordMaster.Meals.COLUMN_NAME_CARBOHYDRATES,carbs);
        values.put(MealRecordMaster.Meals.COLUMN_NAME_PROTEINS, protein);
        values.put(MealRecordMaster.Meals.COLUMN_NAME_FATS, fat);
        values.put(MealRecordMaster.Meals.COLUMN_NAME_VITAMINS,vit);
        values.put(MealRecordMaster.Meals.COLUMN_NAME_MINERALS,min);
        values.put(MealRecordMaster.Meals.COLUMN_NAME_FIBRE, fibre);

        long newRowID = db.insert(MealRecordMaster.Meals.MEALS_TABLE_NAME,null,values);
        System.out.println(newRowID);
        return newRowID; //if val>0 success
    }

    public void deleteMealsInfo(String req){
        SQLiteDatabase db = getReadableDatabase();

        String selection = MealRecordMaster.Meals.COLUMN_NAME_MEALID + " LIKE ?";
        String []selectionArgs = {req};

        db.delete(MealRecordMaster.Meals.MEALS_TABLE_NAME, selection,selectionArgs);
    }

    public int updateMealsInfo(String id,String cal, String carbs, String protein, String fat,
                               String vit, String min, String fibre){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(MealRecordMaster.Meals.COLUMN_NAME_CALORIES, cal);
        values.put(MealRecordMaster.Meals.COLUMN_NAME_CARBOHYDRATES,carbs);
        values.put(MealRecordMaster.Meals.COLUMN_NAME_PROTEINS, protein);
        values.put(MealRecordMaster.Meals.COLUMN_NAME_FATS, fat);
        values.put(MealRecordMaster.Meals.COLUMN_NAME_VITAMINS,vit);
        values.put(MealRecordMaster.Meals.COLUMN_NAME_MINERALS,min);
        values.put(MealRecordMaster.Meals.COLUMN_NAME_FIBRE, fibre);

        String selection = MealRecordMaster.Meals.COLUMN_NAME_MEALID + " LIKE ?";
        String []selectionArgs = {id};

        int count = db.update(
                MealRecordMaster.Meals.MEALS_TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
        return count;
    }

    public Cursor displayAllBreakfast(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select mealID,name,calories," +
                "carbohydrates,proteins,fats,vitamin,mineral,fibre from meals " +
                "where (type= 'Breakfast' or type='breakfast') and date = (select date('now'))",
                null);

        return cursor;
    }

    public Cursor displayAllLunch(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select mealID,name,calories,"+
                "carbohydrates,proteins,fats,vitamin,mineral,fibre from meals" +
                " where type= 'Lunch' or type='lunch' and date = (select date('now')) ",
                null);

        return cursor;
    }

    public Cursor displayAllDinner(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select mealID,name,calories," +
                "carbohydrates,proteins,fats,vitamin,mineral,fibre " +
                "from meals where type= 'Dinner' or type='dinner' and date = (select date('now'))",
                null);

        return cursor;
    }

    public List displayAllMealLog(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                MealRecordMaster.Meals.COLUMN_NAME_MEALID,
                MealRecordMaster.Meals.COLUMN_NAME_DATE,
                MealRecordMaster.Meals.COLUMN_NAME_Name,
                MealRecordMaster.Meals.COLUMN_NAME_TYPE
        };

        //retrives the query from db
        Cursor cursor = db.query(
                MealRecordMaster.Meals.MEALS_TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        List meals = new ArrayList<>();

        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndexOrThrow(
                    MealRecordMaster.Meals.COLUMN_NAME_Name));
            String type = cursor.getString(cursor.getColumnIndexOrThrow(
                    MealRecordMaster.Meals.COLUMN_NAME_TYPE));

            meals.add(name);
            meals.add(type);
        }
        cursor.close();
        return meals;
    }

    public List displayOneMealDetail(String req) {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                MealRecordMaster.Meals.COLUMN_NAME_Name,
                MealRecordMaster.Meals.COLUMN_NAME_CALORIES,
                MealRecordMaster.Meals.COLUMN_NAME_CARBOHYDRATES,
                MealRecordMaster.Meals.COLUMN_NAME_PROTEINS,
                MealRecordMaster.Meals.COLUMN_NAME_FATS,
                MealRecordMaster.Meals.COLUMN_NAME_VITAMINS,
                MealRecordMaster.Meals.COLUMN_NAME_MINERALS,
                MealRecordMaster.Meals.COLUMN_NAME_FIBRE
        };

        String selection = MealRecordMaster.Meals.COLUMN_NAME_MEALID + " LIKE ?";
        String []selectionArgs = {req};

        //retrives the query from db
        Cursor cursor = db.query(
                MealRecordMaster.Meals.MEALS_TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        List meals = new ArrayList<>();

        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndexOrThrow(
                    MealRecordMaster.Meals.COLUMN_NAME_Name));
            String cal = cursor.getString(cursor.getColumnIndexOrThrow(
                    MealRecordMaster.Meals.COLUMN_NAME_CALORIES));
            String carbs = cursor.getString(cursor.getColumnIndexOrThrow(
                    MealRecordMaster.Meals.COLUMN_NAME_CARBOHYDRATES));
            String prot = cursor.getString(cursor.getColumnIndexOrThrow(
                    MealRecordMaster.Meals.COLUMN_NAME_PROTEINS));
            String fats = cursor.getString(cursor.getColumnIndexOrThrow(
                    MealRecordMaster.Meals.COLUMN_NAME_FATS));
            String vit = cursor.getString(cursor.getColumnIndexOrThrow(
                    MealRecordMaster.Meals.COLUMN_NAME_VITAMINS));
            String min = cursor.getString(cursor.getColumnIndexOrThrow(
                    MealRecordMaster.Meals.COLUMN_NAME_MINERALS));
            String fibre = cursor.getString(cursor.getColumnIndexOrThrow(
                    MealRecordMaster.Meals.COLUMN_NAME_FIBRE));

            meals.add(name);
            meals.add(cal);
            meals.add(carbs);
            meals.add(prot);
            meals.add(fats);
            meals.add(vit);
            meals.add(min);
            meals.add(fibre);
        }
        cursor.close();
        return meals;
    }

    public double totalFoodCalories(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select sum(calories) AS Total from meals" +
                " where date= (Select date('now'))",null);

        double total = 0;
        if(cursor.moveToFirst()){
            total = cursor.getDouble(cursor.getColumnIndexOrThrow("Total"));
        }

        return total;
    }

    public double totalCarbohydrates(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select sum(carbohydrates) AS Total from meals" +
                        " where date = (Select date('now'))",
                null);

        double total = 0;
        if(cursor.moveToFirst()){
            total = cursor.getDouble(cursor.getColumnIndexOrThrow("Total"));
        }

        return total;
    }

    public double totalProteins(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select sum(proteins) AS Total from meals" +
                        " where date = (Select date('now'))", null);

        double total = 0;
        if(cursor.moveToFirst()){
            total = cursor.getDouble(cursor.getColumnIndexOrThrow("Total"));
        }

        return total;
    }

    public double totalFats(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select sum(fats) AS Total from meals" +
                " where date = (Select date('now'))",null);

        double total = 0;
        if(cursor.moveToFirst()){
            total = cursor.getDouble(cursor.getColumnIndexOrThrow("Total"));
        }

        return total;
    }

    public double totalVitamins(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select sum(vitamin) AS Total from meals " +
                "where date = (Select date('now'))",null);

        double total = 0;
        if(cursor.moveToFirst()){
            total = cursor.getDouble(cursor.getColumnIndexOrThrow("Total"));
        }

        return total;
    }

    public double totalMinerals(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select sum(mineral) AS Total from meals" +
                " where date = (Select date('now'))",null);

        double total = 0;
        if(cursor.moveToFirst()){
            total = cursor.getDouble(cursor.getColumnIndexOrThrow("Total"));
        }

        return total;
    }

    public double totalFibre(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select sum(fibre) AS Total from meals" +
                " where date = (Select date('now'))",null);

        double total = 0;
        if(cursor.moveToFirst()){
            total = cursor.getDouble(cursor.getColumnIndexOrThrow("Total"));
        }

        return total;
    }

    public boolean checkIfNameExists(String name, String type){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("Select name, type from meals where (" +
                "Select date('now'))",null);

        boolean status = false;
        while(cursor.moveToNext()){
            if(name.equalsIgnoreCase(cursor.getString(cursor.getColumnIndexOrThrow(
                    MealRecordMaster.Meals.COLUMN_NAME_Name)))
            && type.equalsIgnoreCase(cursor.getString(cursor.getColumnIndexOrThrow(
                    MealRecordMaster.Meals.COLUMN_NAME_TYPE)))) {
                status = true;
                break;
            }
        }
        return status;
    }
}

package com.example.myapp2.database.DBHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapp2.database.Master.ExerciseRecordMaster;

import java.util.ArrayList;
import java.util.List;

public class ExerciseDBHandler extends DBHelper{

    public ExerciseDBHandler(Context context) {
        super(context);
    }

    public long addExericeInfo (String date,String name, String minutes, String calBurnt){
        SQLiteDatabase db = getWritableDatabase();
        //adds value to tbl
        ContentValues values = new ContentValues();
        values.put(ExerciseRecordMaster.Exercises.COLUMN_NAME_DATE, date);
        values.put(ExerciseRecordMaster.Exercises.COLUMN_NAME_Name,name);
        values.put(ExerciseRecordMaster.Exercises.COLUMN_NAME_MINUTES,minutes);
        values.put(ExerciseRecordMaster.Exercises.COLUMN_NAME_CALORIESBURNT,calBurnt);

        long newRowID = db.insert(ExerciseRecordMaster.Exercises.EXERCISE_TABLE_NAME,null,values);
        System.out.println(newRowID);
        return newRowID; //if val>0 success
    }

    public Cursor displayAllExercises(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select exerciseID,name,minutes,caloriesBurnt " +
                "from exercises where date = (select date('now'))",null);

        return cursor;
    }

//    public List displayAllExercises(){
//        SQLiteDatabase db = getReadableDatabase();
//
//        String[] projection = {
//                ExerciseRecordMaster.Exercises.COLUMN_NAME_EXERCISEID,
//                ExerciseRecordMaster.Exercises.COLUMN_NAME_DATE,
//                ExerciseRecordMaster.Exercises.COLUMN_NAME_Name
//        };
//
//        //retrives the query from db
//        Cursor cursor = db.query(
//                ExerciseRecordMaster.Exercises.EXERCISE_TABLE_NAME,
//                projection,
//                null,
//                null,
//                null,
//                null,
//                null
//        );
//
//        List exercises = new ArrayList<>();
//
//        while(cursor.moveToNext()){
//            String exerciseid = cursor.getString(cursor.getColumnIndexOrThrow(ExerciseRecordMaster.Exercises.COLUMN_NAME_EXERCISEID));
//            String date = cursor.getString(cursor.getColumnIndexOrThrow(ExerciseRecordMaster.Exercises.COLUMN_NAME_DATE));
//            String name = cursor.getString(cursor.getColumnIndexOrThrow(ExerciseRecordMaster.Exercises.COLUMN_NAME_Name));
//
//            exercises.add(exerciseid);
//            exercises.add(date);
//            exercises.add(name);
//        }
//
//        cursor.close();
//        if (exercises.isEmpty())
//            return null;
//        else
//            return exercises;
//    }

    public void deleteExerciseInfo(String req){
        SQLiteDatabase db = getReadableDatabase();

        String selection = ExerciseRecordMaster.Exercises.COLUMN_NAME_EXERCISEID + " LIKE ?";
        String []selectionArgs = {req};

        db.delete(ExerciseRecordMaster.Exercises.EXERCISE_TABLE_NAME, selection,selectionArgs);
    }

    public int updateExerciseInfo( String exerciseid, String minutes, String calBurnt){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(ExerciseRecordMaster.Exercises.COLUMN_NAME_MINUTES,minutes);
        values.put(ExerciseRecordMaster.Exercises.COLUMN_NAME_CALORIESBURNT,calBurnt);

        String selection = ExerciseRecordMaster.Exercises.COLUMN_NAME_EXERCISEID + " LIKE ?";
        String []selectionArgs = {exerciseid};

        int count = db.update(
                ExerciseRecordMaster.Exercises.EXERCISE_TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
        return count;
    }

    public List displayOneExercise(String req){
        SQLiteDatabase database = getReadableDatabase();
        String[] projection = {
                ExerciseRecordMaster.Exercises.COLUMN_NAME_Name,
                ExerciseRecordMaster.Exercises.COLUMN_NAME_MINUTES,
                ExerciseRecordMaster.Exercises.COLUMN_NAME_CALORIESBURNT
        };

        String selection = ExerciseRecordMaster.Exercises.COLUMN_NAME_EXERCISEID + " LIKE ?";
        String []selectionArgs = {req};
        Cursor cursor = database.query(ExerciseRecordMaster.Exercises.EXERCISE_TABLE_NAME,
                projection,
                selection,
             selectionArgs,
                null,
                null,
                null
    );

        List exercises = new ArrayList<>();

       while(cursor.moveToNext()){
           String name = cursor.getString(cursor.getColumnIndexOrThrow(ExerciseRecordMaster.Exercises.COLUMN_NAME_Name));
           double min = cursor.getDouble(cursor.getColumnIndexOrThrow(ExerciseRecordMaster.Exercises.COLUMN_NAME_MINUTES));
           double calBurnt = cursor.getDouble(cursor.getColumnIndexOrThrow(ExerciseRecordMaster.Exercises.COLUMN_NAME_CALORIESBURNT));

           exercises.add(name);
           exercises.add(min);
           exercises.add(calBurnt);
       }

       cursor.close();
       if (exercises.isEmpty())
           return null;
       else
           return exercises;
    }

    public double totalCalBurnt(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select sum(caloriesBurnt) AS Total from exercises" +
                " where date= (Select date('now'))",null);

        double total = 0;
        if(cursor.moveToFirst()){
            total = cursor.getDouble(cursor.getColumnIndexOrThrow("Total"));
        }

        return total;
    }

    public double totalMinutes(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select sum(minutes) AS Total from exercises" +
                " where date= (Select date('now'))",null);

        double total = 0;
        if(cursor.moveToFirst()){
            total = cursor.getDouble(cursor.getColumnIndexOrThrow("Total"));
        }

        return total;
    }

    public boolean checkIfNameExists(String name){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                ExerciseRecordMaster.Exercises.COLUMN_NAME_Name,
        };

        String selection = ExerciseRecordMaster.Exercises.COLUMN_NAME_DATE + " = ?";
        String []selectionArgs = {getDate()};
        Cursor cursor = db.query(ExerciseRecordMaster.Exercises.EXERCISE_TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean status = false;
        while(cursor.moveToNext()){
            if(name.equalsIgnoreCase(cursor.getString(cursor.getColumnIndexOrThrow(ExerciseRecordMaster.Exercises.COLUMN_NAME_Name)))) {
                status = true;
                break;
            }
        }
        return status;
    }
    public String getDate(){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT date('now') as TIME",null);

        String date = null;
        if (cursor.moveToFirst())
         date = cursor.getString(cursor.getColumnIndexOrThrow("TIME"));

        return date;
    }
}

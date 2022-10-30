package com.example.myapp2.Wellbeingdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapp2.model.SleepModel;

import java.util.ArrayList;

public class DevSleepDB extends SQLiteOpenHelper {
    public DevSleepDB(Context context) {
        super(context,"sleepdb.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table sleepData(id TEXT primary key,date TEXT UNIQUE,hours INTEGER,minutes INTEGER,user TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists sleepData");
    }

    public boolean insertSleepData(String id,String date, int hours, int minutes, String user){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("date",date);
        contentValues.put("hours",hours);
        contentValues.put("minutes",minutes);
        contentValues.put("user",user);
        contentValues.put("id",id);

        long result = DB.insert("sleepData",null,contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
    // id,dateText,fluidIntake
    public boolean updateSleepData(String id, String date, int hours, int minutes){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("date",date);
        contentValues.put("hours",hours);
        contentValues.put("minutes",minutes);
        //  contentValues.put("user",user);
        Cursor cursor = DB.rawQuery("Select * from sleepData where id = ?", new String[] {id});
        if(cursor.getCount()>0){
            long result = DB.update("sleepData",contentValues,"id=?",new String[] {id});
            if(result == -1){
                return false;
            }else{
                return true;
            }
        }
        else{
            return false;
        }

    }

    public boolean deleteSleepData(String id){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        // contentValues.put("date",date);
        // contentValues.put("litres",litres);
        //  contentValues.put("user",user);
        Cursor cursor = DB.rawQuery("Select * from sleepData where id = ?", new String[] {id});
        if(cursor.getCount()>0){
            long result = DB.delete("sleepData","id=?",new String[] {id});
            if(result == -1){
                return false;
            }else{
                return true;
            }
        }
        else{
            return false;
        }

    }

    public ArrayList<SleepModel> getAll(){
        ArrayList<SleepModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM sleepData"  ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToNext()){
            //loop through the Cursor and create new User objects, and put them in the return list
            do {
                String id = cursor.getString(0);
                String date = cursor.getString(1);
                int hours = cursor.getInt(2);
                int minutes = cursor.getInt(3);

                SleepModel sleepEntry = new SleepModel(id,"Dave", date,hours,minutes);
                returnList.add(sleepEntry);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from sleepData",null);
        return cursor;
    }

}

package com.example.myapp2.Wellbeingdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapp2.model.DevLogData;

import java.util.ArrayList;

public class DaveDBHelper extends SQLiteOpenHelper {
    public DaveDBHelper(Context context) {
        super(context,"userdata.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table fluidData(id TEXT primary key,date TEXT UNIQUE, litres REAL,user TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists fluidData");
    }

    public boolean insertlitreData(String id,String date, float litres, String user){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("date",date);
        contentValues.put("litres",litres);
        contentValues.put("user",user);
        contentValues.put("id",id);

        long result = DB.insert("fluidData",null,contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
   // id,dateText,fluidIntake
    public boolean updatelitreData(String id, String date, float litres){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("date",date);
        contentValues.put("litres",litres);
      //  contentValues.put("user",user);
        Cursor cursor = DB.rawQuery("Select * from fluidData where id = ?", new String[] {id});
        if(cursor.getCount()>0){
            long result = DB.update("fluidData",contentValues,"id=?",new String[] {id});
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

    public boolean deletelitreData(String id){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

       // contentValues.put("date",date);
      // contentValues.put("litres",litres);
        //  contentValues.put("user",user);
        Cursor cursor = DB.rawQuery("Select * from fluidData where id = ?", new String[] {id});
        if(cursor.getCount()>0){
            long result = DB.delete("fluidData","id=?",new String[] {id});
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

    public ArrayList<DevLogData> getAll(){
        ArrayList<DevLogData> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM fluidData"  ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToNext()){
            //loop through the Cursor and create new User objects, and put them in the return list
            do {
                String date = cursor.getString(1);
                //int age = cursor.getInt(1);
                float litres = cursor.getFloat(2);
                String id = cursor.getString(0);

                DevLogData fluidEntry = new DevLogData(id,"Dave", date,litres);
                returnList.add(fluidEntry);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from fluidData",null);
        return cursor;
    }

}

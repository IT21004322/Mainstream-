package com.example.myapp2;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import androidx.annotation.ContentView;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class userDBHelper extends SQLiteOpenHelper {


    public static final String DBNAME = "Sculptr.db";
    public static final int DB_VERSION = 1;

    public userDBHelper( Context context) {
        super(context, "Sculptr.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
        MyDB.execSQL(Constants.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {

        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists " + Constants.TABLE_NAME);
        onCreate(MyDB);

    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase MYDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MYDB.insert("users",null,contentValues);
        if(result== -1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username= ?" , new String[] {username});
        if(cursor.getCount() > 0)
            return  true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB= this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username= ? and password = ?", new String[] {username, password});
        if(cursor.getCount() > 0)
            return  true;
        else
            return false;
    }


    // ------------ USER PROFILE ------------

    public long insertInfo(String name,  String email, String age,String birthdate,  String addtime, String updateTime){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.NAME, name);
        values.put(Constants.EMAIL, email);
        values.put(Constants.AGE, age);
        values.put(Constants.BIRTHDATE, birthdate);
        values.put(Constants.ADD_TIMESTAMP, addtime);
        values.put(Constants.UPDATE_TIMESTAMP, updateTime);

        long id = MyDB.insert(Constants.TABLE_NAME, null, values);
        MyDB.close();
        return id;



    }


    public void updateInfo(String id, String name,  String email, String age,String birthdate,  String addtime, String updateTime){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.NAME, name);
        values.put(Constants.EMAIL, email);
        values.put(Constants.AGE, age);
        values.put(Constants.BIRTHDATE, birthdate);
        values.put(Constants.ADD_TIMESTAMP, addtime);
        values.put(Constants.UPDATE_TIMESTAMP, updateTime);

        MyDB.update(Constants.TABLE_NAME, values, Constants.ID+ " = ?" , new String[]{id});
        MyDB.close();

    }

    public  Boolean updateUserInfo(String id, String name,  String email, String age,String birthdate,  String addtime, String updateTime )
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.NAME, name);
        values.put(Constants.EMAIL, email);
        values.put(Constants.AGE, age);
        values.put(Constants.BIRTHDATE, birthdate);
        values.put(Constants.ADD_TIMESTAMP, addtime);
        values.put(Constants.UPDATE_TIMESTAMP, updateTime);

        Cursor cursor  = MyDB.rawQuery("select * from  " + Constants.TABLE_NAME + " where " + Constants.ID+ " = ?" , new String[]{id});

        if(cursor.getCount()>0){

            long result = MyDB.update(Constants.TABLE_NAME, values, Constants.ID+ " = ?" , new String[]{id});
            if(result==1){
                return false;
            }
            else{
                return true;
            }
        }else{
            return false;
        }
    }


    public  Boolean deleteUserInfo(String id, String name )
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();


        Cursor cursor  = MyDB.rawQuery("select * from  " + Constants.TABLE_NAME + " where " + Constants.ID+ " = ?" , new String[]{id});

        if(cursor.getCount()>0){

            long result = MyDB.delete(
                    Constants.TABLE_NAME, Constants.ID+ " = ?" , new String[]{id});
            if(result==1){
                return false;
            }
            else{
                return true;
            }
        }else{
            return false;
        }
    }


    public ArrayList<Model> getAlldata(String orderBy){
        ArrayList<Model> arrayList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME + " ORDER BY " + orderBy;

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery(selectQuery, null);

        if(cursor.moveToNext()){
            do {
                 @SuppressLint("Range") Model model =new Model(
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.ID)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.NAME)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.AGE)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.EMAIL)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.BIRTHDATE)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.ADD_TIMESTAMP)),
                        ""+cursor.getString(cursor.getColumnIndex(Constants.UPDATE_TIMESTAMP))


                );

                arrayList.add(model);
            }while (cursor.moveToNext());
        }
        MyDB.close();
        return  arrayList;
    }

}

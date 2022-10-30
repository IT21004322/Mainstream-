package com.example.myapp2.database.DBHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapp2.database.Master.WeightRecordMaster;

public class WeightDBHandler extends DBHelper {

    public WeightDBHandler(Context context) {
        super(context);
    }

    public long addWeightInfo (String date, String weight){
        SQLiteDatabase db = getWritableDatabase();
        //adds value to tbl
        ContentValues values = new ContentValues();
        values.put(WeightRecordMaster.Weights.COLUMN_NAME_DATE, date);
        values.put(WeightRecordMaster.Weights.COLUMN_NAME_WEIGHT,weight);

        //calculate the change value

        //values.put(WeightRecordMaster.WeightRecord.COLUMN_NAME_CHANGE,0);

        long newRowID = db.insert(WeightRecordMaster.Weights.WEIGHT_TABLE_NAME,null,values);
        System.out.println(newRowID);
        return newRowID; //if val>0 success
    }

    public Cursor displayAllWeight(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from weights order By date DESC",null);
//        String[] projection = {
//                WeightRecordMaster.Weights.COLUMN_NAME_WEIGHTID,
//                WeightRecordMaster.Weights.COLUMN_NAME_DATE,
//                WeightRecordMaster.Weights.COLUMN_NAME_WEIGHT,
//                WeightRecordMaster.Weights.COLUMN_NAME_CHANGE
//        };
        //displays in desc order
//        String sortOrder = WeightRecordMaster.Weights.COLUMN_NAME_DATE + " DESC";
//        //retrives the query from db
//        Cursor cursor = db.query(
//                WeightRecordMaster.Weights.WEIGHT_TABLE_NAME,
//                projection,
//                null,
//                null,
//                null,
//                null,
//                null
//        );
//
//        List details = new ArrayList<>();
//        //List weights = new ArrayList<>();
//        //List changes = new ArrayList<>();

        return cursor;
    }

    public void deleteWeightInfo(String req){
        SQLiteDatabase db = getReadableDatabase();

        String selection = WeightRecordMaster.Weights.COLUMN_NAME_DATE + " = ?";
        String []selectionArgs = {req};

        db.delete(WeightRecordMaster.Weights.WEIGHT_TABLE_NAME, selection,selectionArgs);
    }

    public int updateWeightInfo(String date, String weight){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(WeightRecordMaster.Weights.COLUMN_NAME_WEIGHT,weight);

        String selection = WeightRecordMaster.Weights.COLUMN_NAME_DATE + " = ?";
        String []selectionArgs = {date};

        int count = db.update(
                WeightRecordMaster.Weights.WEIGHT_TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
        return count;
    }

    public double calcAvg(){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("Select avg(weight) as AVERAGE from weights",null);

        double total = 0;
        if(cursor.moveToFirst()){
            total = cursor.getDouble(cursor.getColumnIndexOrThrow("AVERAGE"));
        }
        return total;
    }
//    public void displayChange(){
//        SQLiteDatabase db = getReadableDatabase();
//
//        Cursor cursor = db.rawQuery("Select t.date,t.weight," +
//                "sum(t1.weight - t1.we)",null)
//
//        double total =0;
//
//        return total;
//    }
}


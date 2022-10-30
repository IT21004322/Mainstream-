package com.example.myapp2.database.DBHandler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapp2.database.Master.MealRecordMaster;
import com.example.myapp2.database.Master.ExerciseRecordMaster;
import com.example.myapp2.database.Master.MealRecordMaster;
import com.example.myapp2.database.Master.WeightRecordMaster;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Sculptr.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //creating meal table
        String MEAL_TABLE =
                "CREATE TABLE " + MealRecordMaster.Meals.MEALS_TABLE_NAME + " (" +
                        MealRecordMaster.Meals.COLUMN_NAME_MEALID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        MealRecordMaster.Meals.COLUMN_NAME_DATE+ " DATE NOT NULL," +
                        MealRecordMaster.Meals.COLUMN_NAME_Name+ " TEXT NOT NULL," +
                        MealRecordMaster.Meals.COLUMN_NAME_TYPE + " TEXT," +
                        MealRecordMaster.Meals.COLUMN_NAME_CALORIES+ " DOUBLE,"+
                        MealRecordMaster.Meals.COLUMN_NAME_CARBOHYDRATES +" DOUBLE,"+
                        MealRecordMaster.Meals.COLUMN_NAME_PROTEINS+" DOUBLE,"+
                        MealRecordMaster.Meals.COLUMN_NAME_FATS+" DOUBLE,"+
                        MealRecordMaster.Meals.COLUMN_NAME_VITAMINS +" DOUBLE,"+
                        MealRecordMaster.Meals.COLUMN_NAME_MINERALS +" DOUBLE,"+
                        MealRecordMaster.Meals.COLUMN_NAME_FIBRE + " DOUBLE" + ")";

        sqLiteDatabase.execSQL(MEAL_TABLE);

        //creating weights table
        String WEIGHT_TABLE =
                "CREATE TABLE " + WeightRecordMaster.Weights.WEIGHT_TABLE_NAME + " (" +
                        WeightRecordMaster.Weights.COLUMN_NAME_WEIGHTID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        WeightRecordMaster.Weights.COLUMN_NAME_DATE + " DATE NOT NULL UNIQUE, " +
                        WeightRecordMaster.Weights.COLUMN_NAME_WEIGHT + " DOUBLE " + ")";

        sqLiteDatabase.execSQL(WEIGHT_TABLE);

        //creating exercise table
        String EXERCISE_TABLE = "CREATE TABLE " + ExerciseRecordMaster.Exercises.EXERCISE_TABLE_NAME + " (" +
                ExerciseRecordMaster.Exercises.COLUMN_NAME_EXERCISEID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ExerciseRecordMaster.Exercises.COLUMN_NAME_DATE+ " DATE NOT NULL, " +
                ExerciseRecordMaster.Exercises.COLUMN_NAME_Name + " TEXT NOT NULL, " +
                ExerciseRecordMaster.Exercises.COLUMN_NAME_MINUTES + " DOUBLE, "+
                ExerciseRecordMaster.Exercises.COLUMN_NAME_CALORIESBURNT + " DOUBLE "+ ")";

        sqLiteDatabase.execSQL(EXERCISE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

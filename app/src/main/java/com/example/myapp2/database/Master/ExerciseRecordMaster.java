package com.example.myapp2.database.Master;

import android.provider.BaseColumns;

public final class ExerciseRecordMaster {

    private ExerciseRecordMaster(){

    }

    public static class Exercises implements BaseColumns {

        public static final String EXERCISE_TABLE_NAME = "exercises";
        public static final String COLUMN_NAME_EXERCISEID = "exerciseID";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_Name = "name";
        public static final String COLUMN_NAME_MINUTES = "minutes";
        public static final String COLUMN_NAME_CALORIESBURNT = "caloriesBurnt";
    }
}

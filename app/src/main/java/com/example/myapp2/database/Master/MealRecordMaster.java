package com.example.myapp2.database.Master;

import android.provider.BaseColumns;

public final class MealRecordMaster {

    private MealRecordMaster(){

    }

    public static class Meals implements BaseColumns {

        public static final String MEALS_TABLE_NAME = "meals";
        public static final String COLUMN_NAME_MEALID = "mealID";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_Name = "name";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_CALORIES = "calories";
        public static final String COLUMN_NAME_CARBOHYDRATES =  "carbohydrates";
        public static final String COLUMN_NAME_PROTEINS = "proteins";
        public static final String COLUMN_NAME_FATS = "fats";
        public static final String COLUMN_NAME_VITAMINS = "vitamin";
        public static final String COLUMN_NAME_MINERALS = "mineral";
        public static final String COLUMN_NAME_FIBRE = "fibre";

    }
}

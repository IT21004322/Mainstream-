package com.example.myapp2.database.Master;

import android.provider.BaseColumns;

public final class WeightRecordMaster {

    private WeightRecordMaster() {
    }

    public static class Weights implements BaseColumns {
        public static final String WEIGHT_TABLE_NAME = "weights";
        public static final String COLUMN_NAME_WEIGHTID = "weightID";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_WEIGHT = "weight";
        public static final String COLUMN_NAME_CHANGE= "change";
    }
}

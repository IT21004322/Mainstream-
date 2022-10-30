package com.example.myapp2;

import static org.junit.Assert.assertEquals;

import com.example.myapp2.models.MealRecords;

import org.junit.Before;
import org.junit.Test;

public class MealRecordsTest {
    private MealRecords mealRecords;

    @Before
    public void setUp(){
        mealRecords = new MealRecords();
    }

    @Test
    public void testValidateValues(){
        boolean result = mealRecords.validateValues("20","20","43","12",
                "3","5.9","2.3");
        assertEquals(true,result);
    }

    @Test
    public void testValidateType(){
        boolean result = mealRecords.validateType("type");
        assertEquals(false,result);
    }

    @Test
    public void testValidateTypeIgnoreCase(){
        boolean result = mealRecords.validateType("DiNneR");
        assertEquals(true,result);
    }

    @Test
    public void testValidateName(){
        boolean result = mealRecords.validateName(null);
        assertEquals(false,result);
    }
}

package com.example.myapp2;

import static org.junit.Assert.assertEquals;

import com.example.myapp2.models.WeightRecords;

import org.junit.Before;
import org.junit.Test;

public class WeightRecordsTest {
    private WeightRecords weightRecords;

    @Before
    public void setUp(){
        weightRecords = new WeightRecords();
    }

    @Test
    public void testValidateWeight(){
        boolean negativeResult = weightRecords.validateWeight("-10");
        assertEquals(false,negativeResult);
    }

}

package com.example.myapp2;

import static org.junit.Assert.assertEquals;

import com.example.sculptr.models.ExerciseRecords;

import org.junit.Before;
import org.junit.Test;

public class ExerciseRecordsTest {
    private ExerciseRecords exerciseRecords;

    @Before
    public void setUp(){
        exerciseRecords = new ExerciseRecords();
    }

    @Test
    public void testValidateValues(){
        boolean result = exerciseRecords.validateValues("-20","-9");
        assertEquals(false,result);
    }

    @Test
    public void testValidateName(){
        boolean result = exerciseRecords.validateName(null);
        assertEquals(false,result);
    }
}

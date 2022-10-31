package com.example.myapp2;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModelTest {

    private Model model;

    @Before public void setup (){
        model = new Model();

    }
    @Test public void testCheckNull(){
        Boolean result= model.checkNull("Chanukya" , "chanukya10", "chanukya10");

        assertEquals(false, result) ;
    }

}

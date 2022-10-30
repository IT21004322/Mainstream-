package com.example.myapp2.Minod;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class insertMeals extends AppCompatActivity {

    EditText name, desc, calories, carbs, proteins, fats, type, bmi;
    Button submit;
    com.example.sculptr.DBhelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_meals);

        name = findViewById(R.id.mealName);
        desc = findViewById(R.id.mealDesc);
        calories = findViewById(R.id.mealCalories);
        carbs = findViewById(R.id.mealCarbs);
        proteins = findViewById(R.id.mealProtein);
        fats = findViewById(R.id.mealFats);
        type = findViewById(R.id.mealType);
        bmi = findViewById(R.id.mealBMI);
        submit = findViewById(R.id.insertMeal);

        DB = new com.example.sculptr.DBhelper(insertMeals.this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.example.sculptr.mealPlanModel meal = null;

                try {
                    meal = new com.example.sculptr.mealPlanModel(name.getText().toString(),
                            desc.getText().toString(),
                            Integer.parseInt(calories.getText().toString()),
                            Integer.parseInt(carbs.getText().toString()),
                            Integer.parseInt(proteins.getText().toString()),
                            Integer.parseInt(fats.getText().toString()),
                            type.getText().toString(),
                            bmi.getText().toString()
                    );
                    if(DB.InsertMealData(meal)){
                        Toast.makeText(insertMeals.this, "Data Entered", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(insertMeals.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), insertMeals.class);
                    startActivity(intent);
                }

    }});
}}
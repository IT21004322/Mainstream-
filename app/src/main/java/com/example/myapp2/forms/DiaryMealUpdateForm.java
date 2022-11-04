package com.example.myapp2.forms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp2.DiaryMealInformation;
import com.example.myapp2.R;
import com.example.myapp2.database.DBHandler.MealsDBHandler;
import com.example.myapp2.models.MealRecords;

public class DiaryMealUpdateForm extends AppCompatActivity {

    int mealid;
    TextView tv_name;
    EditText ed_calories,ed_carbohydrates, ed_protein, ed_fats,ed_vitamin, ed_mineral, ed_fibre;

    public void clearControls(){
        ed_calories.setText("");
        ed_carbohydrates.setText("");
        ed_protein.setText("");
        ed_fats.setText("");
        ed_vitamin.setText("");
        ed_mineral.setText("");
        ed_fibre.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_activity_meal_update_form);

        mealid = getIntent().getIntExtra("mealID",0);
        String name = getIntent().getStringExtra("name");
        double cal = getIntent().getDoubleExtra("cal",0);
        double carbs = getIntent().getDoubleExtra("carbs",0);
        double prot = getIntent().getDoubleExtra("prot",0);
        double fat = getIntent().getDoubleExtra("fat",0);
        double vit = getIntent().getDoubleExtra("vit",0);
        double min = getIntent().getDoubleExtra("min",0);
        double fibre = getIntent().getDoubleExtra("fibre",0);

        tv_name = findViewById(R.id.edit_meal);
        ed_calories = (EditText) findViewById(R.id.edit_calories);
        ed_carbohydrates = (EditText) findViewById(R.id.edit_carbs);
        ed_protein = (EditText) findViewById(R.id.edit_protein);
        ed_fats = (EditText)findViewById(R.id.edit_fats);
        ed_vitamin =(EditText) findViewById(R.id.edit_vitamin);
        ed_mineral =(EditText) findViewById(R.id.edit_mineral);
        ed_fibre = (EditText)findViewById(R.id.edit_fibre);

        tv_name.setText(name);
        ed_calories.setText(String.valueOf(cal));
        ed_carbohydrates.setText(String.valueOf(carbs));
        ed_protein.setText(String.valueOf(prot));
        ed_fats.setText(String.valueOf(fat));
        ed_vitamin.setText(String.valueOf(vit));
        ed_mineral.setText(String.valueOf(min));
        ed_fibre.setText(String.valueOf(fibre));

    }

    public void updateData(View view) {
        MealsDBHandler dbHandler = new MealsDBHandler(this);
        MealRecords validate = new MealRecords();

        boolean validateVal = validate.validateValues(ed_calories.getText().toString(),
                ed_carbohydrates.getText().toString(),ed_protein.getText().toString(),
                ed_fats.getText().toString(),ed_vitamin.getText().toString(),ed_mineral.getText().toString(),
                ed_fibre.getText().toString());

        if (validateVal == true) {
            int val = dbHandler.updateMealsInfo(String.valueOf(mealid),ed_calories.getText().toString(),
                    ed_carbohydrates.getText().toString(),ed_protein.getText().toString(),
                    ed_fats.getText().toString(),ed_vitamin.getText().toString(),ed_mineral.getText().toString(),
                    ed_fibre.getText().toString());

            clearControls();

            if(val > 0 ) {
                Toast.makeText(this, "Data successfully updated", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this,"Data not inserted",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(DiaryMealUpdateForm.this, DiaryMealInformation.class);
            i.putExtra("mealID",mealid);
            startActivity(i);
        } else {
            Toast.makeText(this, "Calories, carbohydrates, proteins, " +
                            "fats, vitamins, minerals and fibre",
                    Toast.LENGTH_LONG).show();
            Toast.makeText(this," needs to be greater than 0",
                    Toast.LENGTH_LONG).show();
        }
    }

}
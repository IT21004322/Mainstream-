package com.example.myapp2.forms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sculptr.DiaryMainActivity;
import com.example.sculptr.R;
import com.example.sculptr.database.DBHandler.MealsDBHandler;
import com.example.sculptr.models.MealRecords;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DiaryMealLogForm extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText calories,meal ,carbs, protein, fats,vitamin, mineral, fibre;
    String strDate;
    Spinner spinner;
    String type;

    public void clearControls(){
        meal.setText("");
        //type.setText("");
        calories.setText("");
        carbs.setText("");
        protein.setText("");
        fats.setText("");
        vitamin.setText("");
        mineral.setText("");
        fibre.setText("");
    }

    String[] typeOptions = {"Choose option...","Breakfast","Lunch","Dinner"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_activity_meal_log_form);


        spinner  = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                typeOptions);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spinner.setAdapter(ad);

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,R.array.options
        ,R.layout.diary_activity_meal_log_form);
        adapter.setDropDownViewResource(R.layout.diary_activity_meal_log_form);

        meal = findViewById(R.id.edit_meal);
        calories = findViewById(R.id.edit_calories);
        carbs = findViewById(R.id.edit_carbs);
        protein = findViewById(R.id.edit_protein);
        fats = findViewById(R.id.edit_fats);
        vitamin = findViewById(R.id.edit_vitamin);
        mineral = findViewById(R.id.edit_mineral);
        fibre = findViewById(R.id.edit_fibre);

        //type = findViewById(R.id.edit_type);
//        System.out.println("Type: "+type);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        strDate = sdf.format(c.getTime());

    }

    public void AddData(View view){
        MealsDBHandler dbHelper = new MealsDBHandler(this);
        MealRecords validate = new MealRecords();

        boolean status = validate.validateName(meal.getText().toString());
        if (status == true){
            int index = spinner.getSelectedItemPosition();
            type = typeOptions[index];

            boolean valType = validate.validateType(type);
            System.out.println(valType);
            if(valType == true){

                boolean exists = dbHelper.checkIfNameExists(meal.getText().toString(),
                        type);

                if (exists == false) {
                    boolean validateVal = validate.validateValues(calories.getText().toString(),
                            carbs.getText().toString(), protein.getText().toString(),
                            fats.getText().toString(), vitamin.getText().toString(),
                            mineral.getText().toString(), fibre.getText().toString());

                    System.out.println("Validate values: " + validateVal);
                    if (validateVal == true) {
                        long val = dbHelper.addMealsInfo(strDate, meal.getText().toString(),
                                type, calories.getText().toString(),
                                carbs.getText().toString(), protein.getText().toString(),
                                fats.getText().toString(), vitamin.getText().toString(),
                                mineral.getText().toString(), fibre.getText().toString());

                        clearControls();

                        if (val > 0) {
                            Toast.makeText(this, "Data successfully inserted",
                                    Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(this, "Meal name already exists",
                                    Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(DiaryMealLogForm.this,
                                DiaryMainActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(this, "Calories, carbohydrates, proteins, " +
                                        "fats, vitamins, minerals and fibre",
                                Toast.LENGTH_LONG).show();
                        Toast.makeText(this," needs to be greater than 0",
                                Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(this, "Meal name already exists",
                            Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(DiaryMealLogForm.this, DiaryMainActivity.class);
                    startActivity(i);
                }
            }
            else{
                Toast.makeText(this,"Meal type has to be 'Breakfast', 'Lunch' " +
                                "or 'Dinner'",
                        Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this,"Meal name cannot be null",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
// make toastof name of course
        // which is selected in spinner
        Toast.makeText(getApplicationContext(),
                        typeOptions[i],
                        Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
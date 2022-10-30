package com.example.myapp2.forms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp2.DiaryMainActivity;
import com.example.myapp2.R;
import com.example.myapp2.database.DBHandler.ExerciseDBHandler;
import com.example.myapp2.models.ExerciseRecords;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DiaryExerciseLogForm extends AppCompatActivity {

    EditText name, minutes , caloriesBurnt;
    String date;

    public void clearControls(){
        name.setText("");
        minutes.setText("");
        caloriesBurnt.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_activity_exercise_log_form);

        name = findViewById(R.id.edit_activity);
        minutes = findViewById(R.id.edit_minutes);
        caloriesBurnt = findViewById(R.id.edit_calBurnt);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = sdf.format(c.getTime());

    }

    public void AddData(View view) {
        ExerciseDBHandler dbHelper = new ExerciseDBHandler(this);
        ExerciseRecords validate = new ExerciseRecords();

        boolean status = validate.validateName(name.getText().toString());


        if (status == true) {
            boolean exists = dbHelper.checkIfNameExists(name.getText().toString());
            if (exists == false) {
                boolean validateVal = validate.validateValues(minutes.getText().toString(),
                        caloriesBurnt.getText().toString());
                if (validateVal == true) {

                    long val = dbHelper.addExericeInfo(date, name.getText().toString(), minutes.getText().toString(),
                            caloriesBurnt.getText().toString());

                    clearControls();

                    if (val > 0) {
                        Toast.makeText(this, "Data successfully inserted", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(this, "Exercise already exists", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(DiaryExerciseLogForm.this, DiaryMainActivity.class);
                    startActivity(i);

                } else
                    Toast.makeText(this, "Minutes and calories burnt needs to be greater than 0",
                            Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Exercise name exists", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(DiaryExerciseLogForm.this,
                        DiaryMainActivity.class);
                startActivity(i);
            }

        } else {
            Toast.makeText(this, "Exercise name cannot be null",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
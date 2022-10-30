package com.example.myapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp2.database.DBHandler.ExerciseDBHandler;
import com.example.myapp2.forms.DiaryExerciseUpdateForm;

import java.util.List;

public class DiaryExerciseInformation extends AppCompatActivity {

    Button update, delete, goback;
    int exerciseID;
    String name;
    double minutes, calBurnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_activity_exercise_information);

        exerciseID = getIntent().getIntExtra("exerciseID",0);
        name = getIntent().getStringExtra("name");
        minutes = getIntent().getDoubleExtra("minutes",0);
        calBurnt = getIntent().getDoubleExtra("calBurnt",0);

        displayData();

        TextView tv_name, tv_min,tv_calBurnt;

        tv_name = findViewById(R.id.exerciseInfo);
        tv_min = findViewById(R.id.minutesInfo);
        tv_calBurnt = findViewById(R.id.calBurntInfo);
        update = findViewById(R.id.btn_updateExercise);
        delete = findViewById(R.id.btn_deleteExercise);
        goback = findViewById(R.id.btn_goback);

        tv_name.setText(name);
        tv_min.setText(String.valueOf(minutes));
        tv_calBurnt.setText(String.valueOf(calBurnt));

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DiaryExerciseInformation.this, DiaryExerciseUpdateForm.class);
                i.putExtra("exerciseID",exerciseID);
                i.putExtra("name", name);
                i.putExtra("minutes",minutes);
                i.putExtra("calBurnt",calBurnt);
                startActivity(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteData();

            }
        });

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiaryExerciseInformation.this, com.example.sculptr.DiaryMainActivity.class);
                startActivity(intent);
            }
        });

    }
    public void deleteData(){
        ExerciseDBHandler dbHandler = new ExerciseDBHandler(this);

        dbHandler.deleteExerciseInfo(String.valueOf(exerciseID));

        Intent intent = new Intent(DiaryExerciseInformation.this, com.example.sculptr.DiaryMainActivity.class);
        startActivity(intent);
    }

    public void displayData(){
        ExerciseDBHandler dbHandler = new ExerciseDBHandler(this);

        List list = dbHandler.displayOneExercise(String.valueOf(exerciseID));

        name = list.get(0).toString();
        minutes = Double.valueOf(list.get(1).toString());
        calBurnt = Double.valueOf(list.get(2).toString());
    }
}
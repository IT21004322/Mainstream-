package com.example.myapp2.forms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sculptr.DiaryExerciseInformation;
import com.example.sculptr.R;
import com.example.sculptr.database.DBHandler.ExerciseDBHandler;
import com.example.sculptr.models.ExerciseRecords;

public class DiaryExerciseUpdateForm extends AppCompatActivity {

    TextView tv_name;
    EditText ed_min, ed_calBurnt;
    int exerciseID;

    public void clearControls(){
        ed_min.setText("");
        ed_calBurnt.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_activity_exercise_update_form);

        exerciseID= getIntent().getIntExtra("exerciseID",0);
        String name = getIntent().getStringExtra("name");
        double minutes = getIntent().getDoubleExtra("minutes",0);
        double calBurnt = getIntent().getDoubleExtra("calBurnt",0);

        tv_name = findViewById(R.id.edit_activity);
        ed_min = findViewById(R.id.edit_minutes);
        ed_calBurnt = findViewById(R.id.edit_calBurnt);

        tv_name.setText(name);
        ed_min.setText(String.valueOf(minutes));
        ed_calBurnt.setText(String.valueOf(calBurnt));
    }

    public void updateData(View view) {
        ExerciseDBHandler dbHandler = new ExerciseDBHandler(this);
        ExerciseRecords validate = new ExerciseRecords();

        boolean status = validate.validateValues(ed_min.getText().toString(),
                ed_calBurnt.getText().toString());

        if (status == true) {
            int val = dbHandler.updateExerciseInfo(String.valueOf(exerciseID), ed_min.getText().toString(), ed_calBurnt.getText().toString());

            if (val > 0) {
                Toast.makeText(this, "Data successfully updated", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(DiaryExerciseUpdateForm.this, DiaryExerciseInformation.class);
                i.putExtra("exerciseID", exerciseID);
                startActivity(i);

                clearControls();

            } else
                Toast.makeText(this,"Could not update data",Toast.LENGTH_SHORT).show();

        }else
            Toast.makeText(this,"Minutes and calories burnt needs to be greater than 0",
                    Toast.LENGTH_LONG).show();
    }
}
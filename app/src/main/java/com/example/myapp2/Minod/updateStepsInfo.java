package com.example.myapp2.Minod;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class updateStepsInfo extends AppCompatActivity {

    EditText updateSteps;
    Button update;
    TextView dateHint;
    com.example.sculptr.DBhelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_steps_info);

        update = findViewById(R.id.updateStepsSubmitButton);
        updateSteps  = findViewById(R.id.updateStepsBox);
        dateHint = findViewById(R.id.stepsDateUpdateHint);

        Bundle B = getIntent().getExtras();
        String date = B.getString("date");
        dateHint.setHint(date);

        DB = new com.example.sculptr.DBhelper(updateStepsInfo.this);

        View navbar = findViewById(R.id.homeId);
        navbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.example.sculptr.stepsModel steps = null;

                try {
                    steps = new com.example.sculptr.stepsModel("John", date, Integer.parseInt(updateSteps.getText().toString()));
                    Toast.makeText(updateStepsInfo.this, "Data Entered", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(updateStepsInfo.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                }

                boolean success = DB.UpdateStepsData(steps);

                if(success){
                    Intent intent  = new Intent(getBaseContext(), stepFunction.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(updateStepsInfo.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
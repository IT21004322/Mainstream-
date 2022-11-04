package com.example.myapp2.Minod;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class getPlan extends AppCompatActivity {

    EditText nameText, ageBox, heightBox, startingWeightBox, goalWeightBox;
    Button calculate;
    com.example.sculptr.DBhelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_plan);

        nameText = findViewById(R.id.nameText);
        ageBox = findViewById(R.id.agetextBox);
        heightBox = findViewById(R.id.heightBox);
        startingWeightBox = findViewById(R.id.startingWeightBox);
        goalWeightBox = findViewById(R.id.goalWeightBox);
        calculate  = findViewById(R.id.nextButton);
        DB = new com.example.sculptr.DBhelper(getPlan.this);

        View navbar = findViewById(R.id.homeId);
        navbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), com.example.sculptr.MainActivity.class);
                startActivity(intent);
            }
        });

        calculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                com.example.sculptr.user_model UM = null;

                com.example.sculptr.user_model testModel = new com.example.sculptr.user_model();
                if(!testModel.checkNull(nameText.getText().toString(),
                        ageBox.getText().toString(),
                        heightBox.getText().toString(),
                        startingWeightBox.getText().toString(),
                        goalWeightBox.getText().toString())){
                                try {
                                    UM = new com.example.sculptr.user_model(nameText.getText().toString(),
                                            Integer.parseInt(ageBox.getText().toString()),
                                            Integer.parseInt(heightBox.getText().toString()),
                                            Double.parseDouble(startingWeightBox.getText().toString()),
                                            Double.parseDouble(goalWeightBox.getText().toString()));
                                    // boolean checkInsertData = DB.InsertUserData(UM);
                                    if(DB.InsertUserData(UM)){
                                        Toast.makeText(getPlan.this, "New Entry Made", Toast.LENGTH_SHORT).show();
                                        nextActivity();
                                    }  else {
                                        Toast.makeText(getPlan.this, "This User Already Exists", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    Toast.makeText(getPlan.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getBaseContext(), getPlan.class);
                                    startActivity(intent);
                                }
                } else {
                    Toast.makeText(getPlan.this, "Please Enter Valid Inputs", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
        public void nextActivity(){
            Intent intent = new Intent(getBaseContext(), com.example.sculptr.secondActivity.class);
            startActivity(intent);
        }
}

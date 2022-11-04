package com.example.myapp2.Minod;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class editUser extends AppCompatActivity {

    EditText nameText, ageBox, heightBox, startingWeightBox, goalWeightBox;
    Button calculate, delete;
    DBhelper DB;
    Bundle B;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        nameText = findViewById(R.id.nameTextUpdate);
        ageBox = findViewById(R.id.agetextBoxUpdate);
        heightBox = findViewById(R.id.heightBoxUpdate);
        startingWeightBox = findViewById(R.id.startingWeightBoxUpdate);
        goalWeightBox = findViewById(R.id.goalWeightBoxUpdate);
        calculate  = findViewById(R.id.nextButtonUpdate);
        delete  = findViewById(R.id.deleteButtonUpdate2);
        DB = new DBhelper(editUser.this);

        B = getIntent().getExtras();
        String name = B.getString("username");
        nameText.setText(name);

        calculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                user_model UM = null;
                try {
                    UM = new user_model(nameText.getText().toString(),
                            Integer.parseInt(ageBox.getText().toString()),
                            Integer.parseInt(heightBox.getText().toString()),
                            Double.parseDouble(startingWeightBox.getText().toString()),
                            Double.parseDouble(goalWeightBox.getText().toString()));
                    // boolean checkInsertData = DB.InsertUserData(UM);
                    if(DB.InsertUserData(UM)){
                        Toast.makeText(editUser.this, "New Entry Made", Toast.LENGTH_SHORT).show();
                        nextActivity();
                    }  else {
                        Toast.makeText(editUser.this, "This User Already Exists", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(editUser.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), secondActivity.class);
                    startActivity(intent);
                }


            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB.DeleteUserData(name);
                Intent intent = new Intent(getBaseContext(), getPlan.class);
                startActivity(intent);
            }
        });


    }
    public void nextActivity(){
        Intent intent = new Intent(getBaseContext(), secondActivity.class);
        startActivity(intent);
    }
}

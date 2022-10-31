package com.example.myapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateUserActivity extends AppCompatActivity {

    private EditText pName, pAge, pEmail, pBirth;
    Button updateInfo;

    private Uri imageUri;

    private String name, age, email, birth , timeStamp;
    private userDBHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);


        pName = findViewById(R.id.personName);
        pAge = findViewById(R.id.personAge);
        pBirth = findViewById(R.id.personBirth);
        pEmail = findViewById(R.id.personEmail);
        updateInfo=findViewById(R.id.update);



        db = new userDBHelper(this);



        updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData();
                Toast.makeText(UpdateUserActivity.this, "Your profile is successfully updated " +name, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UpdateUserActivity.this, UserDetsActivity.class));
            }
        });



    }

    private void getData() {
        name =""+pName.getText().toString().trim();
        age =""+pAge.getText().toString().trim();
        email =""+pEmail.getText().toString().trim();
        birth =""+pBirth.getText().toString().trim();

        timeStamp=""+System.currentTimeMillis();
        db.insertInfo(
                ""+name,
                ""+email,
                ""+age,
                ""+birth,
                ""+timeStamp,
                ""+timeStamp

        );


    }


    public boolean onSupportNavigateUp(){
        //back button pressed
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
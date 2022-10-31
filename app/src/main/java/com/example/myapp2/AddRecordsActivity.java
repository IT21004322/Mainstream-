package com.example.myapp2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddRecordsActivity extends AppCompatActivity {


    private EditText pName, pAge, pEmail, pBirth;
    Button saveInfo;

    private Uri imageUri;

    private String name, age, email, birth , timeStamp;
    private userDBHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_records);


        pName = findViewById(R.id.personName);
        pAge = findViewById(R.id.personAge);
        pBirth = findViewById(R.id.personBirth);
        pEmail = findViewById(R.id.personEmail);


        saveInfo= findViewById(R.id.addRecordbutton);

        db = new userDBHelper(this);



        saveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getData();
                Toast.makeText(AddRecordsActivity.this, "Your profile is created " +name, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddRecordsActivity.this, UserDetsActivity.class));
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
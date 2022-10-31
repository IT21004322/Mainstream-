package com.example.myapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UserDetsActivity extends AppCompatActivity {


    FloatingActionButton fab;
    RecyclerView mRecyclerView;
    userDBHelper userDB;
    Button delete, update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dets);

        fab = findViewById(R.id.addbutton);
        delete= findViewById(R.id.deletebtn);
        update= findViewById(R.id.upbtn);

        mRecyclerView = findViewById(R.id.recyclerView);
        userDB = new userDBHelper(this);



        showRecord();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDetsActivity.this, UpdateUserActivity.class);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserDetsActivity.this, "Your profile has been removed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserDetsActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDetsActivity.this, BMICalActivity.class);
                intent.putExtra("editMode",false);
                startActivity(intent);
            }
        });
    }

    private void showRecord() {
        userAdapter adapter = new userAdapter(UserDetsActivity.this, userDB.getAlldata(Constants.ADD_TIMESTAMP + " DESC"));
        mRecyclerView.setAdapter(adapter);
    }

    protected void onResume(){
        super.onResume();
        showRecord();
    }
}
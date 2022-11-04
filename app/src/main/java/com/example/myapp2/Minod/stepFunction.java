package com.example.myapp2.Minod;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class stepFunction extends AppCompatActivity {

    Button stepsBtn;
    com.example.sculptr.DBhelper DB;
    ArrayList<com.example.sculptr.stepsModel> steps;
    com.example.sculptr.RVadapter.rvClickListener listener;
    Button edit, delete;
    TextView Banner;
    View navbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_function);

        stepsBtn = findViewById(R.id.stepsButton);


        DB = new com.example.sculptr.DBhelper(stepFunction.this);
        Banner = findViewById(R.id.stepFunctionTopBanner);

        Cursor C =  DB.getStepDataCursor();
        int stepCounter = 0;
        while(C.moveToNext()){
            stepCounter += Integer.parseInt(C.getString(2));
        }

        StringBuilder SB = new StringBuilder();

        SB.append("You Have Walked ").append(stepCounter).append(" Steps So Far");

        Banner.setText(SB);

        /*Intent twointent = getIntent();
        Bundle B = twointent.getExtras();
        if(B!=null){
            String nameS = (String) B.get("name");
            name.setText(nameS);
        }

*/      navbar = findViewById(R.id.homeId);
        navbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), com.example.sculptr.MainActivity.class);
                startActivity(intent);
            }
        });
        // Lookup the recyclerview in activity layout
        RecyclerView rvUsers = (RecyclerView) findViewById(R.id.rvStepLog);

        // Initialize contacts
        steps = DB.getAllStepsLogs();

        // Create adapter passing in the sample user data
        //setOnClickListener();
        com.example.sculptr.RVadapter adapter = new com.example.sculptr.RVadapter(steps, listener);
        // Attach the adapter to the recyclerview to populate items
        rvUsers.setAdapter(adapter);
        // Set layout manager to position the items
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        // That's all!

        stepsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), com.example.sculptr.insertSteps.class);
                startActivity(intent);
            }
        });
    }
    /*private void setOnClickListener() {
        listener = new RVadapter.rvClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                intent.putExtra("username", users.get(position).getName());
                intent.putExtra("age", users.get(position).getAge());
                startActivity(intent);
            }
        };
    }*/
}
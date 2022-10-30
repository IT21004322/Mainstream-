package com.example.myapp2.Minod;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {


    TextView banner;
    MenuItem diary;
    com.example.sculptr.DBhelper DB;
    View navbar;
    CardView make, get;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        banner = findViewById(R.id.welcomebanner);
        make = findViewById(R.id.steps);
        get = findViewById(R.id.getplan);

        DB = new com.example.sculptr.DBhelper(this);
        navbar  = findViewById(R.id.diary);

        make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), stepFunction.class);
                startActivity(intent);
            }
        });
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(DB.GetLatest().moveToNext()){
                    Intent intent = new Intent(getBaseContext(), secondActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getBaseContext(), getPlan.class);
                    startActivity(intent);
                }
            }
        });


        /*navbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   Intent intent = new Intent(getBaseContext(), MainActivity.class);
                   startActivity(intent);
            }
        });*/

        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity();
            }
        });

    }
        public void nextActivity(){
            Intent intent = new Intent(getBaseContext(), secondActivity.class);
            startActivity(intent);
        }
}

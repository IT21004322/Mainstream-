package com.example.myapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class DiaryLogs extends AppCompatActivity {

    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_activity_logs);

        b1 = findViewById(R.id.btn_goback);
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(DiaryLogs.this, DiaryMainActivity.class);
                        startActivity(i);
                    }
                }
        );

    }
    public void changeFragment(View view){
        Fragment fragment;
        if (view == findViewById(R.id.btn_weightLogFrag)){
            fragment = new DiaryWeightLog();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentDefaultLogs,fragment);
            ft.commit();
        }
        if (view == findViewById(R.id.btn_fitnessLogFrag)){
            fragment = new DiaryFitnessLog();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentDefaultLogs,fragment);
            ft.commit();
        }
    }
}
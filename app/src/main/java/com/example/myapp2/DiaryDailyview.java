package com.example.myapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DiaryDailyview extends AppCompatActivity {

    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_activity_dailyview);

        b1 = findViewById(R.id.btn_goback);
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(DiaryDailyview.this, com.example.myapp2.DiaryMainActivity.class);
                        startActivity(i);
                    }
                }
        );

    }
    public void changeFragment(View view){
        Fragment fragment;
        if (view == findViewById(R.id.btn_caloriesFrag)){
            fragment = new DiaryCaloriesFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentDefaultDV,fragment);
            ft.commit();
        }
        if (view == findViewById(R.id.btn_progressFrag)){
            fragment = new DiaryProgressFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentDefaultDV,fragment);
            ft.commit();
        }
    }
}
package com.example.myapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class DevSleepChart extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dave_activity_sleep_schedule);

        MaterialCardView sleepLog = (MaterialCardView) findViewById(R.id.sleepingLog);
        sleepLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DevSleepChart.this, DevSleepUpdate.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onClick(View view) {

    }
}
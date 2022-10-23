package com.example.myapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class DevMainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dave_activity_main);

        CardView card2 = findViewById(R.id.weightChart);
        CardView card1 =  findViewById(R.id.fluid);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DevMainActivity.this, com.example.sculptr.DevFluidChart.class);
                startActivity(i);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DevMainActivity.this, DevSleepChart.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onClick(View view) {

    }
}
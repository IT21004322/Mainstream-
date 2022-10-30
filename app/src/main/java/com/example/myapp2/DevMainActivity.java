package com.example.myapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.myapp2.fluid.DevFluidChart;
import com.example.myapp2.fluid.DevFluidInput;
import com.example.myapp2.sleep.DevSleepChart;
import com.example.myapp2.sleep.DevSleepInput;

public class DevMainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dave_activity_main);

        CardView sleepMain = findViewById(R.id.sleep);
        CardView sleepChart = findViewById(R.id.sleepChart);
        CardView fluid =  findViewById(R.id.fluid);
        CardView fluidChart = findViewById(R.id.fluidIntake);

        sleepMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DevMainActivity.this, DevSleepInput.class);
                startActivity(i);
            }
        });
        fluid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DevMainActivity.this, DevFluidChart.class);
                startActivity(i);
            }
        });
        fluidChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DevMainActivity.this, DevFluidInput.class);
                startActivity(i);
            }
        });

        sleepChart.setOnClickListener(new View.OnClickListener() {
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
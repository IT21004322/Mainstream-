package com.example.myapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class BMICalActivity extends AppCompatActivity {

    android.widget.Button discalculatebmi;
    TextView discurrentHeight , discurrentAge, discurrentWeight;
    ImageView disincrementAge, disincrementWeight, disdecrementAge, disdecrementWeight;
    SeekBar disseekbarforheight;
    RelativeLayout dismale, disfemale;

    int intweight = 55;
    int intage = 22;
    int currentprogress;
    String intprogress = "170";
    String typeOfuser = "0";
    String weight = "55";
    String age = "22" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bmi_calculator);
//        getSupportActionBar().hide();

        //defining variables
        discalculatebmi = findViewById(R.id.calculatebtn);
        discurrentHeight = findViewById(R.id.currentheight);
        discurrentAge =  findViewById(R.id.currentage);
        discurrentWeight = findViewById(R.id.cuurentweight);
        disincrementAge= findViewById(R.id.addAge);
        disincrementWeight = findViewById(R.id.add);
        disdecrementAge = findViewById(R.id.minusAge);
        disdecrementWeight = findViewById(R.id.minus);
        disseekbarforheight =  findViewById(R.id.seekbarforheight);
        dismale = findViewById(R.id.male);
        disfemale = findViewById(R.id.female);


        dismale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismale.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_male_female_bmi));
                disfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.notfocus_male_female_bmi));
                typeOfuser = "Male";
            }
        });

        disfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_male_female_bmi));
                dismale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.notfocus_male_female_bmi));
                typeOfuser = "Female";
            }
        });

        disseekbarforheight.setMax(300);
        disseekbarforheight.setProgress(170);
        disseekbarforheight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentprogress =progress;
                intprogress = String.valueOf(currentprogress);
                discurrentHeight.setText(intprogress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        disincrementAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intage =intage +1;
                age = String.valueOf(intage);
                discurrentAge.setText(age);
            }
        });

        disdecrementAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intage =intage -1;
                age = String.valueOf(intage);
                discurrentAge.setText(age);
            }
        });

        disincrementWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intweight =intweight +1;
                weight = String.valueOf(intweight);
                discurrentWeight.setText(weight);
            }
        });

        disdecrementWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intweight =intweight -1;
                weight = String.valueOf(intweight);
                discurrentWeight.setText(weight);
            }
        });



        discalculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(typeOfuser.equals("0"))
               {
                   Toast.makeText(BMICalActivity.this, "Select your Gender first", Toast.LENGTH_SHORT).show();
               }
               else if(intprogress.equals("0"))
               {
                   Toast.makeText(BMICalActivity.this, "Select the correct height", Toast.LENGTH_SHORT).show();
               }
               else if(intage==0 || intage<0)
               {
                   Toast.makeText(BMICalActivity.this, "Select the correct Age", Toast.LENGTH_SHORT).show();
               }
               else if(intweight==0 || intweight<0)
               {
                   Toast.makeText(BMICalActivity.this, "Select the correct Weight", Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Intent intent = new Intent(BMICalActivity.this ,BMI_Activity.class);

                   intent.putExtra("gender", typeOfuser);
                   intent.putExtra("height" , intprogress);
                   intent.putExtra("age", age);
                   intent.putExtra("weight", weight);

                   startActivity(intent);
               }














            }
        });

    }
}
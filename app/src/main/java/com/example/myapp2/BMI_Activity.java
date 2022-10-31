package com.example.myapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BMI_Activity extends AppCompatActivity {

    android.widget.Button disrecalculatbmi;

    TextView disBMIresult , disBMIcategory, disBMIgender;
    Intent intent;
    ImageView disImageview;
    String disBMI;
    float intBMI;

    String height1;
    String weight1;
    float intHeight, intWeight;
    RelativeLayout disBackground;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

//        getSupportActionBar().setElevation(0);
//        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\"></font>"));
//        getSupportActionBar().setTitle("RESULT");
//        ColorDrawable colorDrawable  =new ColorDrawable(Color.parseColor("#FF5A5F"));
//        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        intent =getIntent();

        //defining variables
        disBMIresult = findViewById(R.id.bmidisplay);
        disBMIcategory = findViewById(R.id.bmicategory);
        disBMIgender = findViewById(R.id.genderdisplay);
        disBackground = findViewById(R.id.contentLayout);
        disImageview = findViewById(R.id.imageView11);
        disrecalculatbmi = findViewById(R.id.recalculatebtn);

        //get the values from intents
        height1 = intent.getStringExtra("height");
        weight1 = intent.getStringExtra("weight");

        //convert to float
        intHeight = Float.parseFloat(height1);
        intWeight = Float.parseFloat(weight1);

        //calculation
        intHeight = intHeight/100;
        intBMI = intWeight/(intHeight*intHeight);

        //convert to string
        disBMI = Float.toString(intBMI);

        //display BMI with the different categories
        if(intBMI<16)
        {
            disBMIcategory.setText("Under weight");
            disBMIcategory.setTextColor(Color.RED);
            disImageview.setImageResource(R.drawable.ok);//cross
        }
        else if (intBMI<16.9 && intBMI>16)
        {
            disBMIcategory.setText("Under weight");
            disBMIcategory.setTextColor(Color.RED);
            disImageview.setImageResource(R.drawable.ok);//warning
        }
        else if(intBMI<18.4 && intBMI>17)
        {
            disBMIcategory.setText("Under weight");
            disBMIcategory.setTextColor(Color.RED);
            disImageview.setImageResource(R.drawable.ok);//warning
        }
        else if(intBMI<25 && intBMI>18.4)
        {
            disBMIcategory.setText("Normal");
            disImageview.setImageResource(R.drawable.ok);//ok
        }

        else if(intBMI<29.4 && intBMI>25)
        {
            disBMIcategory.setText("Overweight");
            disBMIcategory.setTextColor(Color.RED);
            disImageview.setImageResource(R.drawable.ok);//warning
        }
        else
        {
            disBMIcategory.setText("obese class I");
            disBMIcategory.setTextColor(Color.RED);
            disImageview.setImageResource(R.drawable.ok);//warning
        }


        disBMIgender.setText(intent.getStringExtra("gender"));
        disBMIresult.setText(disBMI);


        disrecalculatbmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BMI_Activity.this, BMICalActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
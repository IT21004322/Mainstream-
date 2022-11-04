package com.example.myapp2.Minod;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sculptr.extra.fourthActivity;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class thirdActivity extends AppCompatActivity {

    ImageView lunch, dinner;
    Button nextBtn2;
    private Object ImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        
        lunch = findViewById(R.id.lunchIMG);
        dinner = findViewById(R.id.dinnerIMG);
        nextBtn2 = findViewById(R.id.nextPage3);

        
        Glide.with(this).load(R.drawable.lunch)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(15,1)))
                .into(lunch);

        Glide.with(this).load(R.drawable.dinner)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(15,1)))
                .into(dinner);

        onBtnClick2();
    }

    public void onBtnClick2 (){

        nextBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), fourthActivity.class);
                startActivity(intent);
            }
        });
    }

    
}
package com.example.myapp2.Minod;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class secondActivity extends AppCompatActivity {

    TextView stats, recomendation2;
    RecyclerView rvMeals;
    com.example.sculptr.DBhelper DB;
    ArrayList<com.example.sculptr.mealPlanModel> meal;
    ImageButton nextBtn;
    View navbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        stats = findViewById(R.id.stats);
        rvMeals = findViewById(R.id.recyclerView);
        nextBtn = findViewById(R.id.nextPage2);
        //navbar  = findViewById(R.id.diary);
        DB = new com.example.sculptr.DBhelper(secondActivity.this);

        onBtnClick();

        View navbar = findViewById(R.id.homeId);
        navbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), com.example.sculptr.MainActivity.class);
                startActivity(intent);
            }
        });

       Cursor res = DB.GetLatest();
       StringBuilder buffer = new StringBuilder();
       recomendation2 = findViewById(R.id.recomendation2);

        while(res.moveToNext()){
            String BMI = calcBMI(res.getString(3), res.getString(4), res.getString(2));
            buffer.append("Your name is ").append(res.getString(1));
            buffer.append(". You are ").append(res.getString(2)).append(" years old");
            buffer.append(" with a height of ").append(res.getString(3)).append("CM,");
            buffer.append(" and weight of ").append(res.getString(4)).append("KG. ");
            recomendation2.setText(BMI);
        }

        meal = DB.mealPlanGetAll();


        // Create adapter passing in the sample user data
        com.example.sculptr.mealsRVadapter adapter = new com.example.sculptr.mealsRVadapter(meal);
        // Attach the adapter to the recyclerview to populate items
        rvMeals.setAdapter(adapter);
        // Set layout manager to position the items
        rvMeals.setLayoutManager(new LinearLayoutManager(this));




        //AlertDialog.Builder builder = new AlertDialog.Builder(secondActivity.this);
        //builder.setCancelable(true);
        //builder.setTitle("New Entry Made");
        //builder.setMessage(buffer.toString());
        //builder.show();

        stats.setText(buffer.toString());

       /* navbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });*/

    }

    public void onBtnClick (){
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.GetLatest();
                Intent intent = new Intent(getBaseContext(), com.example.sculptr.editUser.class);
                while(res.moveToNext()){
                    intent.putExtra("username",res.getString(1));
                }
                startActivity(intent);
            }
        });
    }

    //66 + (13.7 X weight in kg) + (5 x height in cm) – (6.8 x age in yrs)

    public String calcBMI(String height, String weight, String age){

        DecimalFormat df = new DecimalFormat("0.00");
        double BMI;
        int A = Integer.parseInt(age);
        double H = Double.parseDouble(height);
        double W = Double.parseDouble(weight);
        String answer = null;

        //BMI = (W / ((H/100)*(H/100)));
        BMI =  (66 + (13.7 * W) + (5 * H) - (6.8 * A));

        if(BMI < 2000){
            answer = "Your Total Daily Energy Expenditure is "+df.format(BMI)+"Kcal, and our recommendation is that you gain some weight in a healthy manner. Adding 500Kcal more to your Diet will be adequate.";
            return answer;
        } else if (BMI >= 2000 && BMI <= 2500){
            answer = "Your Total Daily Energy Expenditure is "+df.format(BMI)+"Kcal, and our recommendation is that you Maintain your Current Weight";
            return answer;
        } else {
            answer = "Your Total Daily Energy Expenditure is "+df.format(BMI)+"Kcal, and our recommendation is that you Lose some Unhealthy Fat. Reducing 500Kcal from your Diet will be adequate.";
            return answer;
        }

    }
}
/*
    Cursor res = DB.GetUserData();
    StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                        buffer.append("Name : " + res.getString(0) + "\n");
                        buffer.append("Age : " + res.getString(1) + "\n");
                        buffer.append("Height : " + res.getString(2) + "\n");
                        buffer.append("Start Weight : " + res.getString(3) + "\n");
                        buffer.append("Goal Weight : " + res.getString(4) + "\n\n");
                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setCancelable(true);
                        builder.setTitle("New Entry Made");
                        builder.setMessage(buffer.toString());
                        builder.show();


                        */
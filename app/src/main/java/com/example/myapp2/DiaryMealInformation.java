package com.example.myapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sculptr.database.DBHandler.MealsDBHandler;
import com.example.sculptr.forms.DiaryMealUpdateForm;

import java.util.List;

public class DiaryMealInformation extends AppCompatActivity {

    Button update, delete, goback;
    int mealid;
    String name;
    double calories, carbohydrates,proteins, fats, vitamins, minerals, fibre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_activity_meal_information);

          mealid = getIntent().getIntExtra("mealID",0);
          System.out.println("mealID: " +mealid);

        displayData();

        TextView tv_name, tv_cal,tv_carbs,tv_prot,tv_fat,tv_vit,tv_min,tv_fibre;

        update = findViewById(R.id.btn_updateMeal);
        delete = findViewById(R.id.btn_deleteMeal);

        tv_name = findViewById(R.id.mealInfo);
        tv_carbs = findViewById(R.id.carbInfo);
        tv_cal = findViewById(R.id.calInfo);
        tv_prot = findViewById(R.id.protInfo);
        tv_fat = findViewById(R.id.fatInfo);
        tv_vit = findViewById(R.id.vitInfo);
        tv_min = findViewById(R.id.minInfo);
        tv_fibre = findViewById(R.id.fibreInfo);
        goback = findViewById(R.id.btn_goback);

        tv_name.setText(name);
        tv_cal.setText(String.valueOf(calories));
        tv_carbs.setText(String.valueOf(carbohydrates));
        tv_prot.setText(String.valueOf(proteins));
        tv_fat.setText(String.valueOf(fats));
        tv_vit.setText(String.valueOf(vitamins));
        tv_min.setText(String.valueOf(minerals));
        tv_fibre.setText(String.valueOf(fibre));

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DiaryMealInformation.this, DiaryMealUpdateForm.class);
                i.putExtra("mealID",mealid);
                i.putExtra("name", name);
                i.putExtra("cal",calories);
                i.putExtra("carbs",carbohydrates);
                i.putExtra("prot",proteins);
                i.putExtra("fat",fats);
                i.putExtra("vit",vitamins);
                i.putExtra("min",minerals);
                i.putExtra("fibre",fibre);

                startActivity(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();

                Intent intent = new Intent(DiaryMealInformation.this, com.example.sculptr.DiaryMainActivity.class);
                startActivity(intent);
            }

        });

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiaryMealInformation.this, com.example.sculptr.DiaryMainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void deleteData() {
        MealsDBHandler dbHandler = new MealsDBHandler(this);

        dbHandler.deleteMealsInfo(String.valueOf(mealid));

        Toast.makeText(this,"Deleted entry successfully",Toast.LENGTH_SHORT).show();
    }

    public void displayData(){
        MealsDBHandler dbHandler = new MealsDBHandler(this);

        List list = dbHandler.displayOneMealDetail(String.valueOf(mealid));

        name = list.get(0).toString();
        calories = Double.valueOf(list.get(1).toString());
        carbohydrates = Double.valueOf(list.get(2).toString());
        proteins = Double.valueOf(list.get(3).toString());
        fats = Double.valueOf(list.get(4).toString());
        vitamins = Double.valueOf(list.get(5).toString());
        minerals = Double.valueOf(list.get(6).toString());
        fibre = Double.valueOf(list.get(7).toString());
    }
}
package com.example.sculptr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sculptr.RecyclerView.DiaryExerciseRecyclerViewAdapter;
import com.example.sculptr.RecyclerView.DiaryMealRecyclerViewAdapter;
import com.example.sculptr.RecyclerView.DiaryRecyclerViewInterface;
import com.example.sculptr.database.DBHandler.ExerciseDBHandler;
import com.example.sculptr.database.DBHandler.MealsDBHandler;
import com.example.sculptr.database.Master.ExerciseRecordMaster;
import com.example.sculptr.database.Master.MealRecordMaster;
import com.example.sculptr.forms.DiaryExerciseLogForm;
import com.example.sculptr.forms.DiaryMealLogForm;
import com.example.sculptr.models.ExerciseRecords;
import com.example.sculptr.models.MealRecords;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class DiaryMainActivity extends AppCompatActivity  {

    Button dailyview, fitness;
    ImageButton addMeal, addExercise;
    RecyclerView breakfastRv ,lunchRv, dinnerRv, exerciseRv;

    ArrayList<Integer> breakfastId, lunchId, dinnerId, exerciseId;
    ArrayList<String> bname, lname,dname,ename;
    ArrayList<Double> bcalories, bcarbs, bproteins, bfats, bvit, bmin, bfibre,lcalories, lcarbs,
            lproteins, lfats, lvit, lmin, lfibre,dcalories, dcarbs, dproteins, dfats, dvit,
            dmin, dfibre, ecalBurnt, eminutes;

    DiaryMealRecyclerViewAdapter bAdapter,lAdapter,dAdapter;
    DiaryExerciseRecyclerViewAdapter exerciseAdapter;
    ExerciseDBHandler exerciseDBHandler;
    MealsDBHandler mealsDBHandler;

    ArrayList<MealRecords> breakfastMealsModel = new ArrayList<>();
    ArrayList<MealRecords> lunchMealsModel = new ArrayList<>();
    ArrayList<MealRecords> dinnerMealsModel = new ArrayList<>();
    ArrayList<ExerciseRecords> exerciseModel = new ArrayList<>();

    DiaryRecyclerViewInterface mealsInterface = new DiaryRecyclerViewInterface() {
        @Override
        public void onMealItemClick(int position,String type) {
            Intent intent = new Intent(DiaryMainActivity.this,DiaryMealInformation.class);

            if (type.equalsIgnoreCase("Breakfast")){
                intent.putExtra("mealID",breakfastMealsModel.get(position).getMealID() );
            }
            else if(type.equalsIgnoreCase("Lunch")){
                intent.putExtra("mealID",lunchMealsModel.get(position).getMealID() );
            }
            else{
                intent.putExtra("mealID",dinnerMealsModel.get(position).getMealID() );
            }
            startActivity(intent);
        }

        @Override
        public void onExerciseItemClick(int position) {}
    };

    DiaryRecyclerViewInterface exerciseInterface = new DiaryRecyclerViewInterface() {
        @Override
        public void onMealItemClick(int position, String type) { }

        @Override
        public void onExerciseItemClick(int position) {
            Intent intent = new Intent(DiaryMainActivity.this,DiaryExerciseInformation.class);
            intent.putExtra("exerciseID",exerciseModel.get(position).getExerciseId());
//            intent.putExtra("name", exerciseModel.get(position).getName());
//            intent.putExtra("minutes",exerciseModel.get(position).getMinutes());
//            intent.putExtra("calBurnt",exerciseModel.get(position).getCaloriesBurnt());
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_activity_main);

        mealsDBHandler = new MealsDBHandler(this);
        exerciseDBHandler = new ExerciseDBHandler(this);

        breakfastId = new ArrayList<>();
        lunchId = new ArrayList<>();
        dinnerId = new ArrayList<>();
        exerciseId = new ArrayList<>();

        bname = new ArrayList<>();
        lname = new ArrayList<>();
        dname = new ArrayList<>();
        ename = new ArrayList<>();
        bcalories =new ArrayList<>();
        bcarbs = new ArrayList<>();
        bproteins = new ArrayList<>();
        bfats = new ArrayList<>();
        bvit = new ArrayList<>();
        bmin  = new ArrayList<>();
        bfibre = new ArrayList<>();
        lcalories = new ArrayList<>();
        lcarbs = new ArrayList<>();
        lproteins = new ArrayList<>();
        lfats = new ArrayList<>();
        lvit  = new ArrayList<>();
        lmin= new ArrayList<>();
        lfibre= new ArrayList<>();
        dcalories= new ArrayList<>();
        dcarbs= new ArrayList<>();
        dproteins= new ArrayList<>();
        dfats= new ArrayList<>();
        dvit= new ArrayList<>();
        dmin= new ArrayList<>();
        dfibre= new ArrayList<>();
        ecalBurnt= new ArrayList<>();
        eminutes= new ArrayList<>();

        breakfastRv = findViewById(R.id.breakfastTbl);
        lunchRv = findViewById(R.id.lunchTbl);
        dinnerRv = findViewById(R.id.dinnerTbl);
        exerciseRv = findViewById(R.id.exerciseTbl);

        displayData();
        setUpData();

        bAdapter = new DiaryMealRecyclerViewAdapter(this, breakfastMealsModel,mealsInterface);
        lAdapter = new DiaryMealRecyclerViewAdapter(this,lunchMealsModel,mealsInterface);
        dAdapter = new DiaryMealRecyclerViewAdapter(this,dinnerMealsModel,mealsInterface);
        exerciseAdapter = new DiaryExerciseRecyclerViewAdapter(this,exerciseModel,exerciseInterface);

        dailyview = findViewById(R.id.btn_dailyView);
        fitness = findViewById(R.id.btn_weightlog);
        addMeal = findViewById(R.id.btn_addMeal);
        addExercise = findViewById(R.id.btn_addExercise);

        breakfastRv.setAdapter(bAdapter);
        lunchRv.setAdapter(lAdapter);
        dinnerRv.setAdapter(dAdapter);
        exerciseRv.setAdapter(exerciseAdapter);

        breakfastRv.setLayoutManager(new LinearLayoutManager(this));
        lunchRv.setLayoutManager(new LinearLayoutManager(this));
        dinnerRv.setLayoutManager(new LinearLayoutManager(this));
        exerciseRv.setLayoutManager(new LinearLayoutManager(this));

        //registerForContextMenu(morebtn);

        dailyview.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Intent i = new Intent(DiaryMainActivity.this, DiaryDailyview.class);
                                             startActivity(i);
                                         }
                                     }
        );

        fitness.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(DiaryMainActivity.this, DiaryLogs.class);
                        startActivity(i);
                    }
                }
        );

        addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DiaryMainActivity.this, DiaryMealLogForm.class);
                startActivity(i);
            }
        });

        addExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DiaryMainActivity.this, DiaryExerciseLogForm.class);
                startActivity(i);
            }
        });
    }

    private void displayData() {
        Cursor bmeal = mealsDBHandler.displayAllBreakfast();
        Cursor lmeal = mealsDBHandler.displayAllLunch();
        Cursor dmeal = mealsDBHandler.displayAllDinner();
        Cursor exercise = exerciseDBHandler.displayAllExercises();

        if (bmeal.getCount() == 0 && lmeal.getCount()==0 &&
                dmeal.getCount()==0 && exercise.getCount() ==0){
            Toast.makeText(this,"There is no data to display",Toast.LENGTH_SHORT).show();
            return;
        }
        else {

            if (bmeal.getCount() == 0) {
                Toast.makeText(this, "There is no breakfast data to display", Toast.LENGTH_SHORT).show();
            } else {
                while (bmeal.moveToNext()) {
                    int mealID = bmeal.getInt(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_MEALID));
                    String name = bmeal.getString(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_Name));
                    double cal = bmeal.getDouble(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_CALORIES));
                    double carbs = bmeal.getDouble(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_CARBOHYDRATES));
                    double prot = bmeal.getDouble(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_PROTEINS));
                    double fats = bmeal.getDouble(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_FATS));
                    double vit = bmeal.getDouble(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_VITAMINS));
                    double min = bmeal.getDouble(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_MINERALS));
                    double fibre = bmeal.getDouble(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_FIBRE));

                    breakfastId.add(mealID);
                    bname.add(name);
                    bcalories.add(cal);
                    bcarbs.add(carbs);
                    bproteins.add(prot);
                    bfats.add(fats);
                    bvit.add(vit);
                    bmin.add(min);
                    bfibre.add(fibre);
                }
            }

            if (lmeal.getCount() == 0) {
                Toast.makeText(this, "There is no lunch data to display", Toast.LENGTH_SHORT).show();
            } else {
                while (lmeal.moveToNext()) {
                    int mealID = lmeal.getInt(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_MEALID));
                    String name = lmeal.getString(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_Name));
                    double cal = lmeal.getDouble(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_CALORIES));
                    double carbs = lmeal.getDouble(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_CARBOHYDRATES));
                    double prot = lmeal.getDouble(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_PROTEINS));
                    double fats = lmeal.getDouble(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_FATS));
                    double vit = lmeal.getDouble(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_VITAMINS));
                    double min = lmeal.getDouble(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_MINERALS));
                    double fibre = lmeal.getDouble(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_FIBRE));

                    lunchId.add(mealID);
                    lname.add(name);
                    lcalories.add(cal);
                    lcarbs.add(carbs);
                    lproteins.add(prot);
                    lfats.add(fats);
                    lvit.add(vit);
                    lmin.add(min);
                    lfibre.add(fibre);
                }
            }

            if (dmeal.getCount() == 0) {
                Toast.makeText(this, "There is no dinner data to display", Toast.LENGTH_SHORT).show();
            } else {
                while (dmeal.moveToNext()) {
                    int mealID = dmeal.getInt(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_MEALID));
                    String name = dmeal.getString(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_Name));
                    double cal = dmeal.getDouble(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_CALORIES));
                    double carbs = dmeal.getDouble(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_CARBOHYDRATES));
                    double prot = dmeal.getDouble(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_PROTEINS));
                    double fats = dmeal.getDouble(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_FATS));
                    double vit = dmeal.getDouble(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_VITAMINS));
                    double min = dmeal.getDouble(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_MINERALS));
                    double fibre = dmeal.getDouble(bmeal.getColumnIndexOrThrow(MealRecordMaster.Meals.COLUMN_NAME_FIBRE));

                    dinnerId.add(mealID);
                    dname.add(name);
                    dcalories.add(cal);
                    dcarbs.add(carbs);
                    dproteins.add(prot);
                    dfats.add(fats);
                    dvit.add(vit);
                    dmin.add(min);
                    dfibre.add(fibre);
                }
            }

            if (exercise.getCount() == 0) {
                Toast.makeText(this, "There is no exercise data to display", Toast.LENGTH_SHORT).show();
            } else {
                while (exercise.moveToNext()) {
                    int exerciseID = exercise.getInt(exercise.getColumnIndexOrThrow(ExerciseRecordMaster.Exercises.COLUMN_NAME_EXERCISEID));
                    String name = exercise.getString(exercise.getColumnIndexOrThrow(ExerciseRecordMaster.Exercises.COLUMN_NAME_Name));
                    double minutes = exercise.getDouble(exercise.getColumnIndexOrThrow(ExerciseRecordMaster.Exercises.COLUMN_NAME_MINUTES));
                    double calBurnt = exercise.getDouble(exercise.getColumnIndexOrThrow(ExerciseRecordMaster.Exercises.COLUMN_NAME_CALORIESBURNT));

                    exerciseId.add(exerciseID);
                    ename.add(name);
                    eminutes.add(minutes);
                    ecalBurnt.add(calBurnt);
                }
            }
        }

        bmeal.close();
        lmeal.close();
        dmeal.close();
        exercise.close();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.context_menu,menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.info:
                Toast.makeText(this, "More selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.updateMeal:
                Toast.makeText(this, "Update meal", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.deleteMeal:
                Toast.makeText(this, "Delete Meal", Toast.LENGTH_SHORT).show();
                return true;
            default:return super.onContextItemSelected(item);
        }
    }

    private void setUpData(){
        for(int j=0;j<bname.size(); j++){
            breakfastMealsModel.add(new MealRecords(breakfastId.get(j), bname.get(j),"breakfast", bcalories.get(j), bcarbs.get(j),
                    bproteins.get(j), bfats.get(j), bvit.get(j), bmin.get(j), bfibre.get(j)));
        }

        for(int j=0;j<lname.size(); j++){
            lunchMealsModel.add(new MealRecords(lunchId.get(j), lname.get(j), "Lunch",lcalories.get(j), lcarbs.get(j),
                    lproteins.get(j), lfats.get(j), lvit.get(j), lmin.get(j), lfibre.get(j)));
        }

        for(int j=0;j<dname.size(); j++){
            dinnerMealsModel.add(new MealRecords(dinnerId.get(j), dname.get(j),"Dinner", dcalories.get(j), dcarbs.get(j),
                    dproteins.get(j), dfats.get(j), dvit.get(j), dmin.get(j), dfibre.get(j)));
        }

        for(int j=0;j<ename.size(); j++){
            exerciseModel.add(new ExerciseRecords(exerciseId.get(j), ename.get(j), eminutes.get(j),
                    ecalBurnt.get(j)));
        }
    }
}
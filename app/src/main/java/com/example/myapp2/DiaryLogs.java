package com.example.myapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp2.database.DBHandler.WeightDBHandler;
import com.example.myapp2.database.Master.WeightRecordMaster;
import com.example.myapp2.RecyclerView.DiaryWeightRecyclerViewAdapter;
import com.example.myapp2.database.DBHandler.ExerciseDBHandler;
import com.example.myapp2.database.DBHandler.WeightDBHandler;
import com.example.myapp2.database.Master.WeightRecordMaster;
import com.example.myapp2.forms.DiaryWeightLogForm;

import java.util.ArrayList;

public class DiaryLogs extends AppCompatActivity {

    Button b1;
    ImageButton add;
    RecyclerView recyclerView;
    ArrayList<String> weightID,day,weight;
    WeightDBHandler dbHandler;
    DiaryWeightRecyclerViewAdapter adapter;
    TextView average;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_activity_logs);

        dbHandler = new WeightDBHandler(this);
        weightID = new ArrayList<>();
        day = new ArrayList<>();
        weight = new ArrayList<>();
        recyclerView = findViewById(R.id.weightrecyclerview);
        adapter = new DiaryWeightRecyclerViewAdapter(day,weight,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayData();

        b1 = findViewById(R.id.btn_goback);
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(DiaryLogs.this, DiaryMainActivity.class);
                        startActivity(i);
                    }
                }
        );

        add = findViewById(R.id.addWeight);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DiaryLogs.this, DiaryWeightLogForm.class);
                startActivity(i);
            }
        });

        average = findViewById(R.id.averageVal);
        average.setText(String.valueOf(dbHandler.calcAvg()));

    }

    public void displayData() {
        Cursor cursor = dbHandler.displayAllWeight();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "There is no data to display", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            while(cursor.moveToNext()){
                String weightid = cursor.getString(cursor.getColumnIndexOrThrow(WeightRecordMaster.Weights.COLUMN_NAME_WEIGHTID));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(WeightRecordMaster.Weights.COLUMN_NAME_DATE));
                String wt = cursor.getString(cursor.getColumnIndexOrThrow(WeightRecordMaster.Weights.COLUMN_NAME_WEIGHT));

                weightID.add(weightid);
                day.add(date);
                weight.add(wt);
            }
        }
    }

}
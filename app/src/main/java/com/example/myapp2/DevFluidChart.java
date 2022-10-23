package com.example.myapp2;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class DevFluidChart extends AppCompatActivity {

    public ArrayList barArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dave_activity_two);
        BarChart barChart = findViewById(R.id.calorieBar);
        getData();
        BarDataSet barDataSet = new BarDataSet(barArrayList, "Monthly Calorie Change");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);

        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        barDataSet.setValueTextColor(Color.BLACK);

        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(true);

        //Assigning X axis labels
        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("Monday");
        xAxisLabel.add("Tuesday");
        xAxisLabel.add("Wednesday");
        xAxisLabel.add("Thursday");
        xAxisLabel.add("Friday");
        xAxisLabel.add("Saturday");
        xAxisLabel.add("Sunday");

        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabel));
    }

    private void getData(){
        barArrayList = new ArrayList();
        barArrayList.add(new BarEntry(0f,1300));
        barArrayList.add(new BarEntry(1f,3400));
        barArrayList.add(new BarEntry(2f,1850));
        barArrayList.add(new BarEntry(3f,3000));
        barArrayList.add(new BarEntry(4f,2340));
        barArrayList.add(new BarEntry(5f,2500));
        barArrayList.add(new BarEntry(6f,2230));

    }
}
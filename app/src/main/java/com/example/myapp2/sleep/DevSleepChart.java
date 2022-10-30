package com.example.myapp2.sleep;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sculptr.R;
import com.example.sculptr.Wellbeingdatabase.DevSleepDB;
import com.example.sculptr.model.SleepModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class DevSleepChart extends AppCompatActivity {

    public ArrayList barArrayList;
    DevSleepDB DB;
    ArrayList<SleepModel> sleeps;
    TextView totalview,averageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dave_activity_sleepchart);

        totalview = findViewById(R.id.sleepTotal);
        averageview = findViewById(R.id.sleepAvg);

        DB = new DevSleepDB(DevSleepChart.this);

        //get all info from database
        sleeps = DB.getAll();
        //assign them to model data
        SleepModel model[] = new SleepModel[sleeps.size()];
        String[] xlabel = new String[sleeps.size()];

        //Testing Id Income
        for(int i = 0; i < sleeps.size();i++){
            model[i] = sleeps.get(i);
            System.out.println(model[i].getId());
        }

        for(int i = 0; i < sleeps.size();i++){
            xlabel[i] = model[i].getDate();
            xlabel[i] = xlabel[i].replace("2022","");
        }

        //Barchart content
        BarChart barChart = findViewById(R.id.sleepChart);
        int count = 0;
        barArrayList = new ArrayList();

        //Creating an Array for hours
        int[] hours = new int[sleeps.size()];
        int[] minutes = new int[sleeps.size()];

        int[] value = new int[sleeps.size()];
        float sum = 0;
        //Assigning hours to an Array
        for(int i = 0;i < sleeps.size();i++){
            hours[i] = model[i].getHours();
            minutes[i]  = model[i].getMinutes();

            value[i] = (hours[i] * 60) + minutes[i];
            sum = sum + value[i];
        }

        float average= sum / sleeps.size();


        totalview.setText(String.format("%.2f",sum/60));
        averageview.setText(String.format("%.2f",average/60));

        //Passing BarEntries
        for(int i = 0;i < sleeps.size();i++){
            barArrayList.add(new BarEntry(count,value[i]));
            System.out.println("Float Value: " +value[i] + " Count: " + count);
            count++;
        }

        //passes information
        BarDataSet barDataSet = new BarDataSet(barArrayList, "Fluid Intake");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.setDrawBarShadow(false);
        barDataSet.setColors(ColorTemplate.rgb("#5FA1A1"));
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(9f);
        barChart.getDescription().setEnabled(false);
        barChart.getXAxis().setEnabled(true);
        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        //Assigning X axis labels
        final ArrayList<String> xAxisLabel = new ArrayList<>();
        for(int i = 0;i < sleeps.size();i++){
            xAxisLabel.add(xlabel[i]);
        }
        //Assigning Litres to an array
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLabel));


    }
//    private void getData(){
//        //String[] xlabel = new String[fluids.size()];
//
////        barArrayList.add(new BarEntry(0,1300));
////        barArrayList.add(new BarEntry(1,3400));
////        barArrayList.add(new BarEntry(2,1850));
////        barArrayList.add(new BarEntry(3,3000));
////        barArrayList.add(new BarEntry(4,2340));
////        barArrayList.add(new BarEntry(5,2500));
////        barArrayList.add(new BarEntry(6,2230));
//
//
//
//    }
}
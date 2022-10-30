package com.example.myapp2.fluid;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sculptr.R;
import com.example.sculptr.Wellbeingdatabase.DaveDBHelper;
import com.example.sculptr.model.DevLogData;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class DevFluidChart extends AppCompatActivity {

    public ArrayList barArrayList;
    DaveDBHelper DB;
    ArrayList<DevLogData> fluids;
    TextView totalview,averageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dave_activity_two);

        totalview = findViewById(R.id.fluidTotal);
        averageview = findViewById(R.id.fluidAvg);

        DB = new DaveDBHelper(DevFluidChart.this);

        //get all info from database
        fluids = DB.getAll();
        //assign them to model data
        DevLogData model[] = new DevLogData[fluids.size()];
        String[] xlabel = new String[fluids.size()];

        for(int i = 0; i < fluids.size();i++){
             model[i] = fluids.get(i);
             System.out.println(model[i].getId());
        }
        for(int i = 0; i < fluids.size();i++){
            xlabel[i] = model[i].getDate();
            xlabel[i] = xlabel[i].replace("2022","");
        }



        //Barchart content
        BarChart barChart = findViewById(R.id.calorieBar);
        int count = 0;
        barArrayList = new ArrayList();
        //Creating an Array for litres
        float[] litres = new float[fluids.size()];

        float sum = 0;
        //Assigning litres to an Array
        for(int i = 0;i < fluids.size();i++){
            litres[i] = model[i].getLitres();
            sum = sum + litres[i];
            System.out.println("Litres: " + litres[i]);
        }

        float average= sum / fluids.size();

        totalview.setText(String.format("%.2f",sum));
        averageview.setText(String.format("%.2f",average));

        //Passing BarEntries
        for(int i = 0;i < fluids.size();i++){
            barArrayList.add(new BarEntry(count,litres[i]));
            System.out.println("Float Value: " +litres[i] + " Count: " + count);
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
        for(int i = 0;i < fluids.size();i++){
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
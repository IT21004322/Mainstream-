package com.example.myapp2.fluid;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sculptr.R;
import com.example.sculptr.Wellbeingdatabase.DaveDBHelper;
import com.example.sculptr.fluid.recyclerview.DevFluidLog;
import com.example.sculptr.model.DevLogData;
import com.google.android.material.card.MaterialCardView;

import java.util.Calendar;

public class DevFluidInput extends AppCompatActivity implements View.OnClickListener{


    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    DaveDBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dave_activity_fluidmain);

        DB = new DaveDBHelper(this);


        MaterialCardView fluidLog = (MaterialCardView) findViewById(R.id.fluidLog);
        initDatePicker();

        EditText litres = findViewById(R.id.inputLitre);

        Button fluidSubmit = findViewById(R.id.fluidSubmit);
        dateButton = findViewById(R.id.fluidDate);
        dateButton.setHint(getTodaysDate());
        DevLogData ob = new DevLogData();
        fluidSubmit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String dateText = dateButton.getText().toString();
                String user = "Dave";
                String id = String.valueOf(System.currentTimeMillis());
                System.out.println(id);
                float fluidIntake = Float.parseFloat(litres.getText().toString());
                String fluidStr = litres.getText().toString();


                Boolean chekcinsertData = DB.insertlitreData(id,dateText,fluidIntake,user);
                if(chekcinsertData == true){
                    Toast.makeText(DevFluidInput.this,"New Entry Inserted",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(DevFluidInput.this,"Error in Entering Value",Toast.LENGTH_SHORT).show();
                }
            }
        });

        fluidLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DevFluidInput.this, DevFluidLog.class);
                startActivity(i);
            }
        });
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get((Calendar.MONTH));
        month = month + 1;

        int day = cal.get((Calendar.DAY_OF_MONTH));
        return makeDateString(day,month,year);
    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener(){
            public void onDateSet(DatePicker datePicker, int year, int month,int day){
                month = month+1;
                String date = makeDateString(day,month,year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get((Calendar.MONTH));
        int day = cal.get((Calendar.DAY_OF_MONTH));

        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(this,dateSetListener, year, month,day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int day, int month, int year){
        return getMonthFormat(month) + " " + day + " " + year;
    }


    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    @Override
    public void onClick(View view) {

    }
}
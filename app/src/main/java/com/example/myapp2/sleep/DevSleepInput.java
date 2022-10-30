package com.example.myapp2.sleep;

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
import com.example.sculptr.Wellbeingdatabase.DevSleepDB;
import com.example.sculptr.model.SleepModel;
import com.google.android.material.card.MaterialCardView;

import java.util.Calendar;

public class DevSleepInput extends AppCompatActivity implements View.OnClickListener{


    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    DevSleepDB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dave_sleep_activity_sleepmain);

        DB = new DevSleepDB(this);
        MaterialCardView sleepLog = (MaterialCardView) findViewById(R.id.sleepingLog);

        initDatePicker();

        EditText litres = findViewById(R.id.inputLitre);

        EditText hours = findViewById(R.id.inputHours);
        EditText minutes = findViewById(R.id.inputMinutes);
        Button sleepSubmit = findViewById(R.id.sleepSubmit);
        dateButton = findViewById(R.id.sleepDate);
        dateButton.setHint(getTodaysDate());

        sleepSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String dateText = dateButton.getText().toString();
                String user = "Dave";
                String id = String.valueOf(System.currentTimeMillis());
                System.out.println(id);
                String hoursStr = hours.getText().toString();
                String minuteStr = minutes.getText().toString();

                SleepModel ob = new SleepModel();
                //String id, String name, String date, int hours, int minutes
                boolean result = ob.checkNull(hoursStr,minuteStr);
                try{
                    if(result == false){
                        int sleepHours = Integer.parseInt(hours.getText().toString());
                        int sleepMinutes = Integer.parseInt(minutes.getText().toString());
                        Boolean checkInsertData = DB.insertSleepData(id,dateText,sleepHours,sleepMinutes,user);
                        if(checkInsertData == true){
                            Toast.makeText(DevSleepInput.this,"New Sleep Entry Inserted",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(DevSleepInput.this,"Error in Entering Sleep Entry",Toast.LENGTH_SHORT).show();
                        }
                }
                }catch (Exception e){
                    Toast.makeText(DevSleepInput.this,"Please Complete the Form",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), DevSleepInput.class);
                    startActivity(intent);
                }

              //  String id,String date, int hours, int minutes, String user

            }
        });

        sleepLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DevSleepInput.this, DevSleepLog.class);
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
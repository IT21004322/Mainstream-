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

import com.example.sculptr.DevMainActivity;
import com.example.sculptr.R;
import com.example.sculptr.Wellbeingdatabase.DevSleepDB;

import java.util.Calendar;

public class DevSleepUpdate extends AppCompatActivity implements View.OnClickListener {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    DevSleepDB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dave_activity_sleepingupdate);

        DB = new DevSleepDB(this);


        // MaterialCardView fluidLog = (MaterialCardView) findViewById(R.id.sleepingLog);
        initDatePicker();

        EditText hours = findViewById(R.id.updateHours);
        EditText minutes = findViewById(R.id.updateMinutes);
        View navbar = findViewById(R.id.insightId);

        Button delete = findViewById(R.id.deleteSleep);
        Button update = findViewById(R.id.sleepUpdate);
        dateButton = findViewById(R.id.updateSleepDate);
        dateButton.setHint(getTodaysDate());

        if(getIntent().getBundleExtra("sleepValues")!= null){
            Bundle bundle = getIntent().getBundleExtra("sleepValues");
            String id = bundle.getString("id");
            System.out.println("ID: +"+id+" OnClick Date: " + bundle.getString("Date"));
            hours.setText(String.valueOf(bundle.getInt("hours")));
            minutes.setText(String.valueOf(bundle.getInt("minutes")));
            dateButton.setHint(bundle.getString("Date"));
            update.setVisibility(View.VISIBLE);
        }


        navbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DevSleepUpdate.this, DevMainActivity.class);
                startActivity(i);
            }
        });
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Bundle bundle = getIntent().getBundleExtra("sleepValues");
                String id = bundle.getString("id");

                Boolean checkDelete = DB.deleteSleepData(id);

                if(checkDelete == true){
                    Toast.makeText(DevSleepUpdate.this,"Entry Deleted Successfully",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(DevSleepUpdate.this, DevSleepLog.class);
                    startActivity(i);
                }else{
                    Toast.makeText(DevSleepUpdate.this,"Error When Deleting. Please Try Again Later",Toast.LENGTH_SHORT).show();
                }
            }





        });

        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // if(getIntent().getBundleExtra("fluidValues")!= null){
                Bundle bundle = getIntent().getBundleExtra("sleepValues");
                String dateText = dateButton.getText().toString();
                //float fluidIntake = Float.parseFloat(litres.getText().toString());
                int sleptHours = Integer.parseInt(hours.getText().toString());
                int sleptMinutes = Integer.parseInt(minutes.getText().toString());

                String id = bundle.getString("id");
                Boolean checkUpdateData = DB.updateSleepData(id,dateText,sleptHours,sleptMinutes);
                if(checkUpdateData == true){
                    Toast.makeText(DevSleepUpdate.this,"Entry Updated",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(DevSleepUpdate.this, DevSleepLog.class);
                    startActivity(i);

                }else{
                    Toast.makeText(DevSleepUpdate.this,"Error in Updating Value",Toast.LENGTH_SHORT).show();
                }
                //  }
            }
        });

//        fluidUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(DevFluidUpdate.this, DevFluidLog.class);
//                startActivity(i);
//            }
//        });
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
            public void onDateSet(DatePicker datePicker, int year, int month, int day){
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
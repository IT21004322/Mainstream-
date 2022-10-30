package com.example.myapp2.forms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp2.DiaryLogs;
import com.example.myapp2.R;
import com.example.myapp2.database.DBHandler.WeightDBHandler;
import com.example.myapp2.models.WeightRecords;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DiaryWeightLogForm extends AppCompatActivity {

    EditText weight;
    TextView date;
    String strDate
            ;
    public void clearControls(){
        date.setText("");
        weight.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_activity_weight_log_form);

        date = findViewById(R.id.edit_date);
        weight = findViewById(R.id.edit_weight);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        strDate = sdf.format(c.getTime());

        date.setText(strDate);
    }

    public void AddData(View view){
        WeightDBHandler dbHelper = new WeightDBHandler(this);
        WeightRecords validate = new WeightRecords();

        boolean status = validate.validateWeight(weight.getText().toString());

        if (status == true){
            long val = dbHelper.addWeightInfo(date.getText().toString(),weight.getText().toString());

            clearControls();

            if (val > 0) {
                Toast.makeText(this, "Data successfully inserted", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this,"An entry with today's date already exists",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(DiaryWeightLogForm.this, DiaryLogs.class);
            startActivity(i);
        }else{
            Toast.makeText(this,"Weight needs to be greater than 0",Toast.LENGTH_SHORT).show();;
        }
    }
}
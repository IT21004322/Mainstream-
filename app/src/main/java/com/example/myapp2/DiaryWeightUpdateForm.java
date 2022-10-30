package com.example.myapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sculptr.database.DBHandler.WeightDBHandler;
import com.example.sculptr.models.WeightRecords;

public class DiaryWeightUpdateForm extends AppCompatActivity {

    TextView date;
    EditText weight;

    public void clearControls(){
        weight.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_activity_weight_update_form);

        String dateIntent = getIntent().getStringExtra("date");
        double weightIntent = getIntent().getDoubleExtra("weight",0);

        date = findViewById(R.id.edit_date);
        weight = findViewById(R.id.edit_weight);

        date.setText(dateIntent);
        weight.setText(String.valueOf(weightIntent));

    }
    public void updateData(View view){
        WeightDBHandler dbHelper = new WeightDBHandler(this);
        WeightRecords validate = new WeightRecords();

        boolean status = validate.validateWeight(weight.getText().toString());

        if (status == true){
            int val = dbHelper.updateWeightInfo(date.getText().toString(),weight.getText().toString());

            clearControls();

            if (val > 0) {
                Toast.makeText(this, "Data successfully updated", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this,"An entry with today's date already exists",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(DiaryWeightUpdateForm.this, DiaryLogs.class);
            startActivity(i);
        }else{
            Toast.makeText(this,"Weight needs to be greater than 0",Toast.LENGTH_SHORT).show();;
        }
    }
}
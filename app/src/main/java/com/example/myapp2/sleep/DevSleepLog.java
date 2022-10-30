package com.example.myapp2.sleep;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sculptr.R;
import com.example.sculptr.Wellbeingdatabase.DevSleepDB;
import com.example.sculptr.model.SleepModel;

import java.util.ArrayList;


public class DevSleepLog extends AppCompatActivity {
    //@Override

    TextView name;
    DevSleepDB db;
    ArrayList <SleepModel> sleep;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dave_activity_sleeplog);

        db = new DevSleepDB(DevSleepLog.this);



        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.recyclerViewSleep);

        sleep = db.getAll();
        DevSleepAdapter adapter = new DevSleepAdapter(sleep);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setAdapter(adapter);
    }
}
package com.example.myapp2.fluid.recyclerview;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sculptr.R;
import com.example.sculptr.Wellbeingdatabase.DaveDBHelper;
import com.example.sculptr.model.DevLogData;

import java.util.ArrayList;

public class DevFluidLog extends AppCompatActivity {
    //@Override

    TextView name;
    DaveDBHelper DB;
    ArrayList<DevLogData> fluids;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dave_activity_fluid_log);

        DB = new DaveDBHelper(DevFluidLog.this);



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        fluids = DB.getAll();
        DevLogAdapter adapter = new DevLogAdapter(fluids);
     //   recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
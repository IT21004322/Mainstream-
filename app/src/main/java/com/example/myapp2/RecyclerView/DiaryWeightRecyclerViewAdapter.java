package com.example.myapp2.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sculptr.DiaryLogs;
import com.example.sculptr.DiaryWeightUpdateForm;
import com.example.sculptr.R;
import com.example.sculptr.database.DBHandler.WeightDBHandler;

import java.util.ArrayList;

public class DiaryWeightRecyclerViewAdapter extends
        RecyclerView.Adapter<DiaryWeightRecyclerViewAdapter.MyViewHolder> {

    private static final String TAG = "diary.recyclerview.RecyclerViewAdapter";
    private ArrayList<String> dayNames = new ArrayList<>();
    private ArrayList<String> weight = new ArrayList<>();
    private Context mContext;

    public DiaryWeightRecyclerViewAdapter(ArrayList<String> dayNames, ArrayList<String>
            weight, Context mContext) {
        this.dayNames = dayNames;
        this.weight = weight;
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_weight_tbl_layout,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }
    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.date.setText(dayNames.get(position));
        holder.weightData.setText(weight.get(position));
        //holder.changeData.setText(changes.get(position));
//            holder.parentLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.d(TAG, "onClick: clicked on"+dayNames.get(position));
//
//                    Toast.makeText(mContext,dayNames.get(position), Toast.LENGTH_SHORT).show();
//                }
//            });
    }
    @Override
    public int getItemCount() {
        return dayNames.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private DiaryWeightRecyclerViewAdapter adapter;
        TextView date, weightData, changeData;
        //ImageButton delete;
        //RelativeLayout parentLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.tblDay);
            weightData = itemView.findViewById(R.id.tblWeight);
            //check this
            itemView.findViewById(R.id.btn_deleteWeight).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    WeightDBHandler dbHandler = new WeightDBHandler(mContext);

                    dbHandler.deleteWeightInfo(date.getText().toString());

                    Intent i = new Intent(mContext,DiaryLogs.class);
                    mContext.startActivity(i);

                }
            });

            itemView.findViewById(R.id.btn_editWeight).setOnClickListener(new
                  View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                          Intent i = new Intent(mContext, DiaryWeightUpdateForm.class);
                          i.putExtra("date",date.getText().toString());
                          i.putExtra("weight",Double.valueOf(weightData.getText().toString()));
                          mContext.startActivity(i);
                      }
                  });
        }
    }

}

package com.example.myapp2.sleep;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sculptr.R;
import com.example.sculptr.model.SleepModel;

import java.util.List;

public class DevSleepAdapter extends RecyclerView.Adapter<DevSleepAdapter.ViewHolder> {

    //Context context;
    List<SleepModel> listData2;

    public DevSleepAdapter(List<SleepModel> listData2) {
        this.listData2 = listData2;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.dev_sleeplog_item,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SleepModel sleep = listData2.get(position);

        String id = sleep.getId();
        System.out.println(id + "Hours: " + sleep.getHours() + "Minutes: " + sleep.getMinutes());

        holder.sleepLogDate.setText(sleep.getDate());
        holder.sleepHours.setText(String.valueOf(sleep.getHours()));
        holder.sleepMinutes.setText(String.valueOf(sleep.getMinutes()));



        holder.edit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //    Context context = view.getContext();
                Bundle bundle = new Bundle();
                bundle.putString("id",sleep.getId());
                bundle.putString("Date",sleep.getDate());
                bundle.putInt("hours", (int) sleep.getHours());
                bundle.putInt("minutes", (int) sleep.getMinutes());

                System.out.println(sleep.getDate() + "Hours : " + sleep.getHours());
                Context context = view.getContext();
                Intent intent = new Intent(context , DevSleepUpdate.class);
                intent.putExtra("sleepValues",bundle);
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return listData2.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView sleepLogDate;
        TextView sleepHours;
        TextView sleepMinutes;
        // TextView fluidLogId;

        CardView edit;

//                fluid.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(DevMainActivity.this, DevFluidChart.class);
//                startActivity(i);
//            }
//        });

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sleepHours = itemView.findViewById(R.id.HoursLog);
            sleepMinutes = itemView.findViewById(R.id.minutesLog);
            sleepLogDate = itemView.findViewById(R.id.sleepLogDate);

            edit = itemView.findViewById(R.id.singleSleepLog);
            //   fluidLogId = itemView.findViewById(R.id.fluidLogId);
        }
    }
}

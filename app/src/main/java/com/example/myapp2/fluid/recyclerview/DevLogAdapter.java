package com.example.myapp2.fluid.recyclerview;

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
import com.example.sculptr.fluid.DevFluidUpdate;
import com.example.sculptr.model.DevLogData;

import java.util.List;

public class DevLogAdapter extends RecyclerView.Adapter<DevLogAdapter.ViewHolder> {

    //Context context;
    List<DevLogData> listData;

    public DevLogAdapter(List<DevLogData> listData) {
        this.listData = listData;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.dave_log_item,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DevLogData fluid = listData.get(position);


        String id = fluid.getId();
        System.out.println(id);

        holder.fluidLogDate.setText(fluid.getDate());
        holder.fluidLogLitres.setText(String.valueOf(fluid.getLitres()));

        holder.edit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
            //    Context context = view.getContext();
                Bundle bundle = new Bundle();
                bundle.putString("id",fluid.getId());
                bundle.putString("Date",fluid.getDate());
                bundle.putDouble("litres",fluid.getLitres());

                System.out.println(fluid.getDate() + "Litres : " + fluid.getLitres());
                Context context = view.getContext();
                Intent intent = new Intent(context , DevFluidUpdate.class);
                intent.putExtra("fluidValues",bundle);
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fluidLogDate;
        TextView fluidLogLitres;
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

            fluidLogDate = itemView.findViewById(R.id.fluidLogDate);
            fluidLogLitres = itemView.findViewById(R.id.litresLog);

            edit = itemView.findViewById(R.id.singleLog);
         //   fluidLogId = itemView.findViewById(R.id.fluidLogId);
        }
    }
}
